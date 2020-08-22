package com.lvym.fdfs.controller;

import com.lvym.fdfs.pojo.Fdfs;
import com.lvym.fdfs.service.IndexService;
import com.lvym.fdfs.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping
    public String index(Model model) {
       List<Fdfs> fdfsList= indexService.selectAll();
       model.addAttribute("fdfsList",fdfsList);
        return "index";
    }
    @GetMapping("/upload/{id}")
    public String toUpload(@PathVariable Integer id,Model model) {
        Fdfs fdfs= indexService.selectById(id);
      model.addAttribute("fdfs",fdfs);
        return "upload";
    }
    @PostMapping("/upload")
    public String upload(Integer id, MultipartFile myfile, Model model) throws Exception {

        FastDFSClient fastDFSClient = new FastDFSClient("fastdfs.conf");
        byte[] bytesFile = myfile.getBytes();
        String fileName = myfile.getOriginalFilename();
        String fileExtNmae = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFile = fastDFSClient.uploadFile(bytesFile, fileExtNmae);

        Fdfs fdfs = new Fdfs();
        fdfs.setFileSize(myfile.getSize());
        fdfs.setFileType(myfile.getContentType());
        fdfs.setGroupName("gruop1");
        fdfs.setId(id);
        fdfs.setOldFilename(fileName);
        fdfs.setRemoteFilepath(uploadFile);
        indexService.updateIndex(fdfs);
        return "redirect:";
    }
    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        try {
            Fdfs fdfs = indexService.selectById(id);
            FastDFSClient fastDFSClient = new FastDFSClient("fastdfs.conf");
            //响应体
            byte[] bytes = fastDFSClient.download2(fdfs.getGroupName(), fdfs.getRemoteFilepath());
           //响应头
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置响应类型---文件类型
            httpHeaders.setContentLength(fdfs.getFileSize());//设置响应时的大小，用于提供下载进度
            httpHeaders.setContentDispositionFormData("attachment",fdfs.getOldFilename());//设置默认下载文件名
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,httpHeaders,HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        indexService.delete(id);

        return "upload";
    }

}
