package com.taotao.service;

import com.taotao.commom.pojo.EUDataGridResult;
 

public interface ItemParamService {
	/**
	 * 商品参数 列表 显示
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getParamList(int page,int rows);
}
