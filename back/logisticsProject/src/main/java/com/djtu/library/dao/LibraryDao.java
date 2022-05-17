package com.djtu.library.dao;

import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.vo.LibraryVo;

import java.util.List;

public interface LibraryDao {

    /**
     * 添加图书馆
     *
     * @param library 图书馆
     * @return 影响条数
     */
    int addLibrary(Library library);

    /**
     * 查询图书馆列表
     *
     * @return 图书馆列表
     */
    List<LibraryVo> getLibraryList();

    /**
     * 根据楼宇id查询对应图书馆数量
     *
     * @param buildingId 楼宇id
     * @return 图书馆数量
     */
    int getLibraryByBuildingId(String buildingId);

    /**
     * 根据图书馆 id 删除图书馆
     *
     * @param id 图书馆id
     * @return 影响条数
     */
    int deleteLibraryById(String id);

    /**
     * 根据图书馆id查询图书馆
     *
     * @param id 图书馆id
     * @return 图书馆
     */
    Library getLibraryById(String id);

    /**
     * 根据图书馆id修改图书馆信息
     *
     * @param library 图书馆id及信息
     * @return 影响条数
     */
    int editLibrary(Library library);
}
