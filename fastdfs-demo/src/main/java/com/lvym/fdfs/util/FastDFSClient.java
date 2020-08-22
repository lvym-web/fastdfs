package com.lvym.fdfs.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class FastDFSClient {

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient1 storageClient = null;
	
	public FastDFSClient(String conf) throws Exception {
		if (conf.contains("classpath:")) {
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getTrackerServer();
		storageServer = null;
		storageClient = new StorageClient1(trackerServer, storageServer);
	}
	
	/**
	 * 上传文件方法
	 * <p>Title: uploadFile</p>
	 * <p>Description: </p>
	 * @param fileName 文件全路径
	 * @param extName 文件扩展名，不包含（.）
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileName, extName, metas);
		return result;
	}
	
	public String uploadFile(String fileName) throws Exception {
		return uploadFile(fileName, null, null);
	}
	
	public String uploadFile(String fileName, String extName) throws Exception {
		return uploadFile(fileName, extName, null);
	}
	
	/**
	 * 上传文件方法
	 * <p>Title: uploadFile</p>
	 * <p>Description: </p>
	 * @param fileContent 文件的内容，字节数组
	 * @param extName 文件扩展名
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		
		String result = storageClient.upload_file1(fileContent, extName, metas);
		return result;
	}
	
	public String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}
	
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		return uploadFile(fileContent, extName, null);
	}
	
	/**
	 * 文件下载
	 * @param filePath 文件地址
	 * @param savePath 本地保存地址
	 */
	public void download(String filePath,String savePath){
		try {
			byte[] bytes = storageClient.download_file1(filePath);
			//IOUtils.write(bytes,new FileOutputStream(savePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件删除 
	 * @param filePath 文件的地址
	 * @return
	 */
	
	public Boolean deleteFile(String filePath){
		try {
			int i = storageClient.delete_file1(filePath);
			return i==0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取文件信息
	 * @param filePath 文件的地址
	 * @return
	 */
	public String getFileInfo(String filePath){
		try {
			FileInfo fileInfo = storageClient.get_file_info1(filePath);
			String sourceIpAddr = fileInfo.getSourceIpAddr();//文件IP地址
			long fileSize = fileInfo.getFileSize();//文件大小
			Date createTimestamp = fileInfo.getCreateTimestamp();//文件创建时间
			long crc32 = fileInfo.getCrc32();//错误码
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

//上传文件controller的方法：
//	 @RequestMapping("/upload")
//    public Result upload(MultipartFile[] files){
//	List<String> list=new ArrayList<>();
//        for (MultipartFile file : files) {
//            String originalFilename = file.getOriginalFilename();//获取当前文件的全路径加名称
//            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);//获取当前上传文件的扩展名
//
//			/**
//
//			进行相应的逻辑操作，保存上传文件的信息到数据库
//			*/
//            try {
//                FastDFSClient fastDFSClient=new FastDFSClient("classpath:config/fdfs_client.conf"); //创建FastDFS的客户端
//                String path = fastDFSClient.uploadFile(file.getBytes(), extName);//获取上传数据的二进制字节码，以扩展名为extName的格式存在文件服务器，返回该文件在文件服务器的路径
//                String url=FILE_SERVER_URL+path;  //拼接服务器的ip和该文件在服务器的路径，可以在网站中查询该图片
//                list.add(url);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new Result(false,"上传失败");
//            }
//        }
//        return new Result(true,"上传成功");
//    }