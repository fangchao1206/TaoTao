package com.taotao.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.fabric.Response;
import com.taotao.service.ItemParamItemService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model,HttpServletResponse response) {
		 //response.setContentType("text/html;charset=utf-8"); //可以在页面设置 
	 
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
}
