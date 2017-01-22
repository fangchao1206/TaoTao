package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.commom.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	 	 
		@Autowired
		private TbItemMapper itemMapper;
		@Autowired
		private TbItemDescMapper itemDescMapper;
		@Autowired
		private TbItemParamItemMapper itemParamItemMapper;
		
		@Override
		public TbItem getItemById(long itemId) {
			
			//TbItem item = itemMapper.selectByPrimaryKey(itemId);
			//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	 
}

		@Override
		public EUDataGridResult getItemList(int page, int rows) {
			//查询商品列表
			TbItemExample example = new TbItemExample();
			//分页处理   pageHelper 插件配置在mapper的pom中  在mapper调用之前使用
			PageHelper.startPage(page, rows);
			List<TbItem> list = itemMapper.selectByExample(example);
			//创建一个返回值对象
			EUDataGridResult result = new EUDataGridResult();
			result.setRows(list);
			//取记录总条数
			PageInfo<TbItem> pageInfo = new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
		}

		@Override
		public void saveItem(TbItem item, String desc, String itemParams) throws Exception {
			Date date = new Date();
			//获得商品id
			long id = IDUtils.genItemId();
			//添加商品信息
			item.setId(id);
			//商品状态，1-正常，2-下架，3-删除
			item.setStatus((byte) 1);
			item.setCreated(date);
			item.setUpdated(date);
			itemMapper.insert(item);
			//添加商品描述
			//创建TbItemDesc对象
			TbItemDesc itemDesc = new TbItemDesc();
			//获得一个商品id
			itemDesc.setItemId(id);
			itemDesc.setItemDesc(desc);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			//插入 商品描述数据 数据
			itemDescMapper.insert(itemDesc);
           //插入 规格参数
		 insertItemParamItem(id, itemParams);
		/* if(...){
			 做一些插入错误 校验
		 }*/
		}
		
		/**
		 * 添加规格参数
		 * <p>Title: insertItemParamItem</p>
		 * <p>Description: </p>
		 * @param itemId
		 * @param itemParam
		 * @return
		 */
		private TaotaoResult insertItemParamItem(Long itemId, String itemParams) {
			//创建一个pojo
			TbItemParamItem itemParamItem = new TbItemParamItem();
			itemParamItem.setItemId(itemId);
			itemParamItem.setParamData(itemParams);
			itemParamItem.setCreated(new Date());
			itemParamItem.setUpdated(new Date());
			//向表中插入数据
			itemParamItemMapper.insert(itemParamItem);
			
			return TaotaoResult.ok();
			
		}
}
