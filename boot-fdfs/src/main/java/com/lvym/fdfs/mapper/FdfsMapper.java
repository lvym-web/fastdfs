package com.lvym.fdfs.mapper;

import com.lvym.fdfs.pojo.Fdfs;
import com.lvym.fdfs.pojo.FdfsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface FdfsMapper {
    int countByExample(FdfsExample example);

    int deleteByExample(FdfsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Fdfs record);

    int insertSelective(Fdfs record);

    List<Fdfs> selectByExample(FdfsExample example);

    Fdfs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Fdfs record, @Param("example") FdfsExample example);

    int updateByExample(@Param("record") Fdfs record, @Param("example") FdfsExample example);

    int updateByPrimaryKeySelective(Fdfs record);

    int updateByPrimaryKey(Fdfs record);
}