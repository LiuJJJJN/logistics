package com.djtu.library.service.serviceImpl;

import com.djtu.exception.LibraryException;
import com.djtu.library.dao.LibraryDao;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.vo.LibraryVo;
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

}
