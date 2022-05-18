package com.djtu.library.service;

import com.djtu.exception.LibraryException;
import com.djtu.exception.NothingException;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.LibTable;
import com.djtu.library.pojo.vo.AddTableVo;
import com.djtu.library.pojo.vo.LibraryVo;
import com.djtu.library.pojo.vo.TablePageConditionVo;

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

    /**
     * 根据图书馆id分页模糊查询对应桌位列表
     *
     * @param vo 图书馆id、模糊查询条件、分页
     * @return 桌位列表
     */
    List<LibTable> getTableListByLibraryId(TablePageConditionVo vo);

    /**
     * [根据模糊查询条件]查询图书馆id对应桌位列表总量
     *
     * @param vo 图书馆id、模糊查询数据
     * @return 数据总量
     */
    int getTableTotalByLibraryId(TablePageConditionVo vo);

    /**
     * 添加图书馆桌位
     *
     * @param vo 图书馆座位vo
     */
    void addLibraryTable(AddTableVo vo) throws LibraryException;

    /**
     * 根据桌位id删除桌位
     *
     * @param id 桌位id
     */
    void deleteLibraryTable(String id) throws LibraryException;

    /**
     * 修改桌位信息
     *
     * @param vo 新桌位信息
     */
    void editLibraryTable(AddTableVo vo) throws LibraryException;
}
