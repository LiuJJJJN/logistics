package com.djtu.library.service;

import com.djtu.exception.LibraryException;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.vo.LibraryVo;

import java.util.List;

public interface LibraryService {

    /**
     * 添加图书馆
     *
     * @param library 图书馆
     */
    void addLibrary(Library library) throws LibraryException;

    /**
     * 查询图书馆列表
     *
     * @return 图书馆列表
     */
    List<LibraryVo> getLibraryList();

    /**
     * 根据图书馆id删除图书馆
     *
     * @param id 图书馆id
     */
    void deleteLibrary(String id) throws LibraryException;

    /**
     * 根据图书馆id修改图书馆信息
     *
     * @param library 图书馆信息
     */
    void editLibrary(Library library) throws LibraryException;
}
