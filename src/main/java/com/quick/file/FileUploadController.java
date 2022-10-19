package com.quick.file;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileUploadController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("test")
    public ResponseModel test(HttpServletRequest request){
		return new ResponseModel(0,"c:/test/aaa.png","success");
    }

	/**
	 * 多图上传
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uploadImgMany", method = {RequestMethod.GET,RequestMethod.POST} )
	public ResponseModel uploadImgMany(HttpServletRequest request){
		try{
			List fileUrl = FileUploadUtil.uploadFilesNochangeName(request);
			return new ResponseModel(0,fileUrl,"success");
		}
		catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			return new ResponseModel(-1,null,ex.getMessage());
		}
	}


	@RequestMapping(value="upload", method = {RequestMethod.GET,RequestMethod.POST} )
    public ResponseModel upload(HttpServletRequest request){
		try{
			String fileUrl = FileUploadUtil.uploadFile(request);
			return new ResponseModel(0,fileUrl,"success");
		}
		catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			return new ResponseModel(-1,null,ex.getMessage());
		}
    }
	@RequestMapping(value="upload2", method = {RequestMethod.GET,RequestMethod.POST} )
	public ResponseModel upload2(HttpServletRequest request){
		try{
			if (request.getCharacterEncoding() == null) {
				request.setCharacterEncoding("UTF-8");
			}
				String fileUrl = FileUploadUtil.uploadFileNochangeName(request);
			return new ResponseModel(0,fileUrl,"success");
		}
		catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			return new ResponseModel(-1,null,ex.getMessage());
		}
	}
	@RequestMapping(value="delete", method = {RequestMethod.GET,RequestMethod.POST} )
	public ResponseModel delete(HttpServletRequest request){
		try{
			Boolean flag = FileUploadUtil.deleteServerFile(request);

			return new ResponseModel(0,flag,"success");
		}
		catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			return new ResponseModel(-1,null,ex.getMessage());
		}
	}



}
