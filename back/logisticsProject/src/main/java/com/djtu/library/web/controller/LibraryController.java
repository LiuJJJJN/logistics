package com.djtu.library.web.controller;

import com.djtu.exception.LibraryException;
import com.djtu.exception.NothingException;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.vo.LibraryVo;
import com.djtu.library.service.LibraryService;
import com.djtu.response.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping("/addLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result addLibrary(@RequestBody @Valid Library library) throws LibraryException {
        libraryService.addLibrary(library);
        return new Result().setCode(200).setMessage("添加图书馆成功");
    }

    @RequestMapping("/getLibraryList.do")
    @ResponseBody
    @RequiresRoles(value = {"管理员"}, logical = Logical.OR)
    public Result getLibraryList() {
        List<LibraryVo> libraryVoList = libraryService.getLibraryList();
        return new Result().setCode(200).setMessage("查询图书馆列表成功").setData(libraryVoList);
    }

    @RequestMapping("/deleteLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result deleteLibrary(@RequestBody Map map) throws LibraryException {
        String id = (String) map.get("id");
        libraryService.deleteLibrary(id);
        return new Result().setCode(200).setMessage("删除图书馆成功");
    }

    @RequestMapping("/editLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result editLibrary(@RequestBody Library library) throws LibraryException {
        libraryService.editLibrary(library);
        return new Result().setCode(200).setMessage("修改图书馆成功");
    }

}
