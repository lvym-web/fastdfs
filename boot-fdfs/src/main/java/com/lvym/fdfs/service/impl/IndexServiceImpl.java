package com.lvym.fdfs.service.impl;

import com.lvym.fdfs.mapper.FdfsMapper;
import com.lvym.fdfs.pojo.Fdfs;
import com.lvym.fdfs.pojo.FdfsExample;
import com.lvym.fdfs.service.IndexService;
import com.lvym.fdfs.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private FdfsMapper fdfsMapper;
    @Override
    public List<Fdfs> selectAll() {
        FdfsExample example=new FdfsExample();

        List<Fdfs> fdfsList = fdfsMapper.selectByExample(example);
        return fdfsList;
    }

    @Override
    public Fdfs selectById(Integer id) {
        return fdfsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateIndex(Fdfs fdfs) {
        fdfsMapper.updateByPrimaryKeySelective(fdfs);
    }

    @Override
    public void delete(Integer id) {
        Fdfs fdfs = fdfsMapper.selectByPrimaryKey(id);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("fastdfs.conf");
            fastDFSClient.deleteFile(fdfs.getRemoteFilepath());

            fdfs.setRemoteFilepath("");
            fdfs.setOldFilename("");
            fdfs.setGroupName("");
            fdfs.setFileType("");
            fdfs.setFileSize(0l);
            fdfsMapper.updateByPrimaryKeySelective(fdfs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
