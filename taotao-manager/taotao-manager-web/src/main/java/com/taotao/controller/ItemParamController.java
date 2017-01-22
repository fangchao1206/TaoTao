package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EUDataGridResult;
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
/*@RequestMapping("/item/param/query/itemcatid/{itemcatid}")
public */
}
