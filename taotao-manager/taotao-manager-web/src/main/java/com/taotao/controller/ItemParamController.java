package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {
@Autowired
ItemParamService itemparamService;
@RequestMapping("/item/param/list")
@ResponseBody
public EUDataGridResult getItemList(Integer page, Integer rows) {
	EUDataGridResult result = itemparamService.getParamList(page, rows);
	System.out.println(result);
	return result;
}
 

@RequestMapping("/item/param/query/itemcatid/{itemcatid}")
@ResponseBody
public TaotaoResult getItemParamByCid( @PathVariable long itemcatid) {
	TaotaoResult result = itemparamService.getItemParamByCid(itemcatid) ;
	 
	return result;
}

//   /item/param/save/"+$("#itemParamAddTable [name=cid]").val()   post参数：paramData

@RequestMapping("/item/param/save/{cid}")
@ResponseBody
public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
	//创建pojo对象
	TbItemParam itemParam = new TbItemParam();
	itemParam.setItemCatId(cid);
	itemParam.setParamData(paramData);
	TaotaoResult result = itemparamService.insertItemParam(itemParam);
	return result;
}
 
}
