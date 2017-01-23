package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List  getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			Map node = new HashMap();
			node.put("id", tbContentCategory.getId());
			node.put("text", tbContentCategory.getName());
			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			node.put("state", tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		 
		//创建一个pojo
				TbContentCategory contentCategory = new TbContentCategory();
				contentCategory.setName(name);
				contentCategory.setIsParent(false);
				//'状态。可选值:1(正常),2(删除)',
				contentCategory.setStatus(1);
				contentCategory.setParentId(parentId);
				contentCategory.setSortOrder(1);
				contentCategory.setCreated(new Date());
				contentCategory.setUpdated(new Date());
				//添加记录
				contentCategoryMapper.insert(contentCategory);
				//查看父节点的isParent列是否为true，如果不是true改成true
				TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
				//判断是否为true
				if(!parentCat.getIsParent()) {
					parentCat.setIsParent(true);
					//更新父节点
					contentCategoryMapper.updateByPrimaryKey(parentCat);
				}
				//返回结果
				return TaotaoResult.ok(contentCategory);

	}

}
