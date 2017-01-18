package com.taotao.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.utils.FtpUtil;
import com.taotao.commom.utils.IDUtils;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService{
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	 @Value("${IMAGE_BASE_URL}")
	 private String IMAGE_BASE_URL;
	@Override
	public Map uploadPic(MultipartFile uploadFile) {
		Map resultMap=new HashMap<>();
		try {// 上传文件功能实现
					// 判断文件是否为空
					if (uploadFile.isEmpty())
						return null;
					// 上传文件以日期为单位分开存放，可以提高图片的查询速度
					String filePath =   new SimpleDateFormat("yyyy").format(new Date()) + "/"
							+ new SimpleDateFormat("MM").format(new Date()) + "/"
							+ new SimpleDateFormat("dd").format(new Date());
		 String originalFilename = uploadFile.getOriginalFilename();
		// 新文件名
		String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
		// 转存文件，上传到ftp服务器
		
		      boolean result=	FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASE_PATH, filePath, newFileName, uploadFile.getInputStream());
		      	if(!result){
		      		resultMap.put("error", 1);//kindedit 规定的格式
		      		resultMap.put("message", "文件上传失败");
		      		
		      		return 	resultMap;
		      	} 
		      		resultMap.put("error", 0);//kindedit 规定的格式
		      		resultMap.put("url",IMAGE_BASE_URL+"/"+filePath+"/"+newFileName  );
		      		return 	resultMap;
		} catch (IOException e) {
			resultMap.put("error", 1);//kindedit 规定的格式
      		resultMap.put("message", "文件上传失败,发生异常");
	
      		return 	resultMap;
			 
		}
		 
//返回结果
}
}