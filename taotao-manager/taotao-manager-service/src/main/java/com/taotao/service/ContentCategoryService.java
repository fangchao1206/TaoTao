package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.TaotaoResult;

/**
 * 内容分类
 * @author fangchao05
 *
 */
public interface ContentCategoryService {
	public List<?>  getCategoryList(long parentId);
	TaotaoResult insertContentCategory(long parentId, String name);
}
