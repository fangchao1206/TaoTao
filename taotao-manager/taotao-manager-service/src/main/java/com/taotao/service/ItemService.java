package com.taotao.service;

 

import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
TbItem getItemById(long itemId);
 EUDataGridResult getItemList(int page, int rows);
 public void saveItem(TbItem item, String desc, String itemParams) throws Exception;
}
