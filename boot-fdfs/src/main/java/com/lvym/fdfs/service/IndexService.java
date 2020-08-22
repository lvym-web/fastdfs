package com.lvym.fdfs.service;

import com.lvym.fdfs.pojo.Fdfs;

import java.util.List;

public interface IndexService {
    List<Fdfs> selectAll();

    Fdfs selectById(Integer id);

    void updateIndex(Fdfs fdfs);

    void delete(Integer id);
}
