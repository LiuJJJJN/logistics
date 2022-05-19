package com.djtu.library.service.serviceImpl;

import com.djtu.exception.LibraryException;
import com.djtu.exception.NothingException;
import com.djtu.library.dao.LibraryDao;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.LibTable;
import com.djtu.library.pojo.vo.*;
import com.djtu.library.service.LibraryService;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryDao libraryDao;

    @Override
    public synchronized void addLibrary(Library library) throws LibraryException {
        library.setId(StringUtil.generateUUID());
        int count = libraryDao.getLibraryByBuildingId(library.getBuildingId());
        if (count != 0) {
            throw new LibraryException("该楼宇已被绑定, 请勿重复绑定");
        }
        int res = libraryDao.addLibrary(library);
        if (res != 1) {
            throw new LibraryException("添加图书馆失败");
        }
    }

    @Override
    public List<LibraryVo> getLibraryList() {
        return libraryDao.getLibraryList();
    }

    @Override
    public void deleteLibrary(String id) throws LibraryException {
        int res = libraryDao.deleteLibraryById(id);
        if (res != 1) {
            throw new LibraryException("删除图书馆失败");
        }
    }

    @Override
    public synchronized void editLibrary(Library library) throws LibraryException {
        // 判断是否更改了楼宇
        Library oldLibrary = libraryDao.getLibraryById(library.getId());
        if (!oldLibrary.getBuildingId().equals(library.getBuildingId())) {
            // 当更改了楼宇时: 判断新楼宇是否被占用
            int count = libraryDao.getLibraryByBuildingId(library.getBuildingId());
            if (count != 0) {
                throw new LibraryException("该楼宇已被绑定, 请勿重复绑定");
            }
        }
        //未更改楼宇或楼宇未被占用时: 修改楼宇信息
        int res = libraryDao.editLibrary(library);
        if (res != 1) {
            throw new LibraryException("图书馆修改失败");
        }
    }

    @Override
    public List<LibTable> getTableListByLibraryId(TablePageConditionVo vo) {
        return libraryDao.getTableListByLibraryId(vo);
    }

    @Override
    public int getTableTotalByLibraryId(TablePageConditionVo vo) {
        return libraryDao.getTableTotalByLibraryId(vo);
    }

    @Override
    public synchronized void addLibraryTable(AddTableVo vo) throws LibraryException {
        List<LibTable> libTableList = libraryDao.getTableByName(vo.getName());
        if (!libTableList.isEmpty()) {
            throw new LibraryException("添加失败, 桌子编号不能重复");
        }
        vo.setId(StringUtil.generateUUID());
        int res = libraryDao.addTable(vo);
        if (res != 1) {
            throw new LibraryException("添加失败");
        }
    }

    @Override
    public void deleteLibraryTable(String id) throws LibraryException {
        int res = libraryDao.deleteTableById(id);
        if (res != 1) {
            throw new LibraryException("输出桌位失败");
        }
    }

    @Override
    public synchronized void editLibraryTable(AddTableVo vo) throws LibraryException {
        List<LibTable> libTableList = libraryDao.getTableByName(vo.getName());
        if (libTableList.size() > 1) {
            throw new LibraryException("修改失败, 桌子编号重复过多, 请练习管理员");
        }
        if (!libTableList.get(0).getId().equals(vo.getId())) {
            throw new LibraryException("修改失败, 桌子编号不允许重复");
        }
        int res = libraryDao.editTable(vo);
        if (res != 1) {
            throw new LibraryException("修改失败");
        }
    }

    @Override
    public String getLibraryIdByName(String name) {
        return libraryDao.getLibraryIdByName(name);
    }

    @Override
    public List<TableOrderInfoVo> getTableListByArea(GetTableVo vo) throws LibraryException {
        vo.setLibrary(libraryDao.getLibraryIdByName(vo.getLibrary()));
        List<TableOrderInfoVo> tableListByArea = libraryDao.getTableListByArea(vo);
        if (tableListByArea.isEmpty()) {
            throw new LibraryException("未查询到桌位信息, 请联系管理员");
        }
        return tableListByArea;
    }

    @Override
    public Integer getTableTotal(GetTableVo vo) {
        vo.setLibrary(libraryDao.getLibraryIdByName(vo.getLibrary()));
        return libraryDao.getTableTotal(vo);
    }

    @Override
    public Integer getFreeTableTotal(GetTableVo vo) {
        vo.setLibrary(libraryDao.getLibraryIdByName(vo.getLibrary()));
        return libraryDao.getFreeTableTotal(vo);
    }

}
