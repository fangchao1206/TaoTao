package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
@Service
public class ItemParamServiceImpl implements ItemParamService {
 @Autowired
 TbItemParamMapper itemParamMapper;
	@Override
	public EUDataGridResult getParamList(int page, int rows) {
		 TbItemParamExample example=new TbItemParamExample();
		//分页处理   pageHelper 插件配置在mapper的pom中  在mapper调用之前使用
			PageHelper.startPage(page, rows);
			List<TbItemParam > list = itemParamMapper.selectByExampleWithBLOBs (example);//要选择withBlob这个 因为param_data 是text类型的
			//创建一个返回值对象
			EUDataGridResult result = new EUDataGridResult();
			result.setRows(list);
			//取记录总条数
			PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
	  
	}
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		 TbItemParamExample example=new TbItemParamExample();
		 Criteria createCriteria = example.createCriteria();
		 createCriteria.andItemCatIdEqualTo(cid);
		 List<TbItemParam > list= itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
			if (list != null && list.size() > 0) {
				return TaotaoResult.ok(list.get(0));
			}
			
			return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
