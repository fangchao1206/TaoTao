package com.taotao.service;

import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
 

public interface ItemParamService {
	/**
	 * 商品参数 列表 显示
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getParamList(int page,int rows);
	
	/**
	 * 根据类目查询 商品的规格参数模板
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(long  cid);
	/**
	 * 添加 规格参数 模板
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult insertItemParam(TbItemParam itemParam);
}
