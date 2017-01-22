package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EUDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/item/save")
	@ResponseBody
 
	/**
	 
	 * @param item
	 * @param desc
	 * @param itemParams 传过来的规格参数
	 * [{"group":"主体","params":[{"k":"品牌","v":"苹果（Apple）"},{"k":"型号","v":"iPhone 6 A1586"},{"k":"颜色","v":"金色"},{"k":"上市年份","v":"2014"}]},{"group":"网络","params":[{"k":"4G网络制式","v":"移动4G(TD-LTE)/联通4G(FDD-LTE)/电信4G(FDD-LTE)"},{"k":"3G网络制式","v":"移动3G(TD-SCDMA)/联通3G(WCDMA)/电信3G（CDMA2000）"},{"k":"2G网络制式","v":"移动2G/联通2G(GSM)/电信2G(CDMA)"}]},{"group":"存储","params":[{"k":"机身内存","v":"16GB ROM"},{"k":"储存卡类型","v":"不支持"}]}]
	 * @return
	 * @throws Exception
	 */
		public TaotaoResult saveItem(TbItem item, String desc,String itemParams) throws Exception {
			//添加商品信息
			itemService.saveItem(item, desc,  itemParams );
			return TaotaoResult.ok();
		}

}