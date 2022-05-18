package com.djtu.library.dao;

import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.LibTable;
import com.djtu.library.pojo.vo.AddTableVo;
import com.djtu.library.pojo.vo.LibraryVo;
import com.djtu.library.pojo.vo.TablePageConditionVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据图书馆id分页模糊查询对应桌位列表
     *
     * @param vo 图书馆id、分页、模糊条件
     * @return 桌位列表
     */
    List<LibTable> getTableListByLibraryId(@Param("vo") TablePageConditionVo vo);

    /**
     * [模糊查询]图书馆id对应的桌位数据列表总量
     *
     * @param vo 模糊查询数据、图书馆id
     * @return 数据总量
     */
    int getTableTotalByLibraryId(@Param("vo") TablePageConditionVo vo);


    /**
     * 添加桌位
     *
     * @param vo 桌位信息
     * @return 影响条数
     */
    int addTable(@Param("vo") AddTableVo vo);

    /**
     * 根据桌子编号查询桌子
     *
     * @param name 桌子编号
     * @return 桌子实例
     */
    LibTable getTableByName(String name);

    /**
     * 根据id删除桌位
     *
     * @param id 桌位id
     * @return 影响条数
     */
    int deleteTableById(String id);

    /**
     * 修改桌位信息
     *
     * @param vo 新桌位vo
     * @return 影响条数
     */
    int editTable(@Param("vo") AddTableVo vo);
}
