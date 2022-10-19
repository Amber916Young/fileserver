package com.quick.file;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;


public class FileUploadUtil {
	
	private static final WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();
	
	private static String uploadFileUri = null;
	
	/**
	 * 文件服务器地址
	 * @return
	 */
	public static String UploadFileUri()
	{
		if (StringUtils.isEmpty(uploadFileUri))
        {
			uploadFileUri = webApplicationContext.getServletContext().getInitParameter("UploadFileUri");
        }

        return uploadFileUri;
	}
	
	public static String getFilePrePath(HttpServletRequest request){
		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = String.format("%s/statics/", pathRoot);
		return path;
	}

    /**
     * 多文件上传
     */
    public static List uploadFilesNochangeName(HttpServletRequest request) throws Exception {
        String directory = request.getParameter("directory");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List fileEntity = new ArrayList<>();
        List<MultipartFile> files = multipartRequest.getFiles("file");
        for(MultipartFile temp : files){
            String filename = temp.getOriginalFilename();
            File tempFile = new File(getFilePrePath(request) + directory, filename);
            File parentFile = tempFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            temp.transferTo(tempFile);
            String fileUrl = String.format("%s/statics/%s/%s", UploadFileUri(), directory, filename);
            fileEntity.add(fileUrl);
        }
        return fileEntity;
    }


    /**
     * 文件上传
     */
    public static String uploadFileNochangeName(HttpServletRequest request) throws Exception {
        String directory = request.getParameter("directory");
        String fileName =  request.getParameter("filename");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        File tempFile = new File(getFilePrePath(request) + directory, fileName);
        File parentFile = tempFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        file.transferTo(tempFile);

        String fileUrl = String.format("%s/statics/%s/%s", UploadFileUri(), directory, fileName);
        return fileUrl;
    }

    /**
     * 文件上传
     */
    public static String uploadFile(HttpServletRequest request) throws Exception {

        String directory = request.getParameter("directory");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        String fileName = file.getOriginalFilename();
        String fileExtName = getExtensionName(fileName);
        //根据时间戳 生成名字
//        String saveFileName = fileExtName == null ? fileName : String.format("qf%d.%s", System.currentTimeMillis(), fileExtName);
        //直接生成UUID
        String saveFileName = UUIDGenerator.getUUID()+"."+fileExtName;

        File tempFile = new File(getFilePrePath(request) + directory, saveFileName);
        File parentFile = tempFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        file.transferTo(tempFile);

        String fileUrl = String.format("%s/statics/%s/%s", UploadFileUri(), directory, saveFileName);
//        String test ="http://localhost:8888/statics/img/"+saveFileName;
        return fileUrl;
//        return test;
    }




    public static boolean deleteServerFile(HttpServletRequest request) {
        boolean delete_flag = false;
        System.out.println("111111111111111111");

        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String[] value = map.get(key);
            System.out.println(key + " = " + value[0]);
        }
        String directory = request.getParameter("directory");
        String url = request.getParameter("url");
        String atype = request.getParameter("atype");
        String fileName = url.substring(url.indexOf(atype));
//        /alidata/tomcat8081/apache-tomcat-8.5.55/webapps
        String filePath = "/alidata/tomcat8081/apache-tomcat-8.5.55/webapps/ROOT/statics/" + fileName;
        System.err.println("filePath====>"+filePath);
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete())
            delete_flag = true;
        else
            delete_flag = false;
        return delete_flag;
    }

    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        
        return null; 
    }
}