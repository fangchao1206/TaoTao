package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EUDataGridResult;
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
			List<TbItemParam > list = itemParamMapper.selectByExample(example);
			//创建一个返回值对象
			EUDataGridResult result = new EUDataGridResult();
			result.setRows(list);
			//取记录总条数
			PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
	  
	}

	 

}
