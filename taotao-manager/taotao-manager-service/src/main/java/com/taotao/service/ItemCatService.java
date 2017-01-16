package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

public interface ItemCatService {
	 
	
	 
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception ;
}
