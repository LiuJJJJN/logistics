package com.djtu.library.web.controller;

import com.djtu.exception.LibraryException;
import com.djtu.exception.NothingException;
import com.djtu.library.pojo.Library;
import com.djtu.library.pojo.LibTable;
import com.djtu.library.pojo.vo.*;
import com.djtu.library.service.LibraryService;
import com.djtu.response.Result;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.settings.service.StudentService;
import com.djtu.settings.service.UserService;
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    private UserService userService;

    /**
     * 添加图书馆
     *
     * @param library 图书馆
     * @return 成功提示
     * @throws LibraryException 失败报错
     */
    @RequestMapping("/addLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result addLibrary(@RequestBody @Valid Library library) throws LibraryException {
        libraryService.addLibrary(library);
        return new Result().setCode(200).setMessage("添加图书馆成功");
    }

    /**
     * 获取图书馆列表
     *
     * @return 图书馆列表
     */
    @RequestMapping("/getLibraryList.do")
    @ResponseBody
    @RequiresRoles(value = {"管理员"}, logical = Logical.OR)
    public Result getLibraryList() {
        List<LibraryVo> libraryVoList = libraryService.getLibraryList();
        return new Result().setCode(200).setMessage("查询图书馆列表成功").setData(libraryVoList);
    }

    /**
     * 根据图书馆名获取图书馆Id
     *
     * @param map 图书馆名
     * @return 图书馆id
     */
    @RequestMapping("/getLibraryIdByName.do")
    @ResponseBody
    @RequiresRoles(value = {"学生"}, logical = Logical.OR)
    public Result getLibraryIdByName(@RequestBody Map map) {
        String name = (String) map.get("name");
        String id = libraryService.getLibraryIdByName(name);
        return new Result().setCode(200).setMessage("查询图书馆Id成功").setData(id);
    }

    /**
     * 根据id删除图书馆
     *
     * @param map 图书馆id
     * @return 成功提示
     * @throws LibraryException 删除失败
     */
    @RequestMapping("/deleteLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result deleteLibrary(@RequestBody Map map) throws LibraryException {
        String id = (String) map.get("id");
        libraryService.deleteLibrary(id);
        return new Result().setCode(200).setMessage("删除图书馆成功");
    }

    /**
     * 根据图书馆id修改图书馆信息
     *
     * @param library 新图书馆数据
     * @return 修改成功
     * @throws LibraryException 修改失败
     */
    @RequestMapping("/editLibrary.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result editLibrary(@RequestBody Library library) throws LibraryException {
        libraryService.editLibrary(library);

        return new Result().setCode(200).setMessage("修改图书馆成功");
    }

    /**
     * 根据图书馆id分页模糊查询对应桌位
     *
     * @param vo 图书馆id、模糊查询条件、分页
     * @return 图书馆桌位列表
     */
    @RequestMapping("/getTableListByLibraryId.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result getTableListByLibraryId(@RequestBody TablePageConditionVo vo) {
        List<LibTable> libTableList = libraryService.getTableListByLibraryId(vo);

        return new Result().setCode(200).setMessage("查询桌位列表成功").setData(libTableList);
    }

    /**
     * 根据图书馆id模糊查询对应桌位总数
     *
     * @param vo 图书馆id、模糊查询条件、分页
     * @return 图书馆桌位列表
     */
    @RequestMapping("/getTableTotalByLibraryId.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result getTableTotalByLibraryId(@RequestBody TablePageConditionVo vo) {
        int total = libraryService.getTableTotalByLibraryId(vo);

        return new Result().setCode(200).setMessage("查询桌位总量成功").setData(total);
    }

    /**
     * 添加桌位
     *
     * @param vo 添加桌位vo
     * @return 成功提示
     * @throws LibraryException 添加失败
     */
    @RequestMapping("/addLibraryTable.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result addLibraryTable(@RequestBody AddTableVo vo) throws LibraryException {
        libraryService.addLibraryTable(vo);

        return new Result().setCode(200).setMessage("添加桌位成功");
    }

    /**
     * 删除桌位
     *
     * @param map 桌位id
     * @return 成功提示
     * @throws LibraryException 删除失败
     */
    @RequestMapping("/deleteTable.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result deleteTable(@RequestBody Map map) throws LibraryException {
        String id = (String) map.get("id");
        libraryService.deleteLibraryTable(id);

        return new Result().setCode(200).setMessage("删除桌位成功");
    }

    /**
     * 修改桌位信息
     *
     * @param vo 桌位信息
     * @return 成功提示
     * @throws LibraryException 修改失败
     */
    @RequestMapping("/editLibraryTable.do")
    @ResponseBody
    @RequiresRoles("管理员")
    public Result editLibraryTable(@RequestBody AddTableVo vo) throws LibraryException {
        libraryService.editLibraryTable(vo);

        return new Result().setCode(200).setMessage("修改桌位成功");
    }

    /**
     * 获取桌位区域信息
     *
     * @param vo 桌位查询信息
     * @return 成功提示
     * @throws LibraryException 查询失败
     */
    @RequestMapping("/getTableListByArea.do")
    @ResponseBody
    @RequiresRoles("学生")
    public Result getTableListByArea(@RequestBody GetTableVo vo) throws LibraryException, NothingException {
        if (vo.getFloor().equals("1")) {
            throw new NothingException("当前楼层 1 层无座位");
        }
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String stuId = userService.getStudentIdByUserId(userId);
        vo.setStuId(stuId);
        List<TableOrderInfoVo> tableOrderInfoVoList = libraryService.getTableListByArea(vo);
        return new Result().setCode(200).setMessage("获取桌位区域信息成功").setData(tableOrderInfoVoList);
    }

    /**
     * 获取桌位总数
     *
     * @param vo 桌位查询信息
     * @return 成功提示
     * @throws LibraryException 查询失败
     */
    @RequestMapping("/getTableTotal.do")
    @ResponseBody
    @RequiresRoles("学生")
    public Result getTableTotal(@RequestBody GetTableVo vo) throws LibraryException, NothingException {
        if (vo.getFloor().equals("1")) {
            throw new NothingException("当前楼层 1 层无座位");
        }
        Integer total = libraryService.getTableTotal(vo);
        return new Result().setCode(200).setMessage("获取桌位总数成功").setData(total);
    }

    /**
     * 获取可用桌位总数
     *
     * @param vo 桌位查询信息
     * @return 成功提示
     * @throws LibraryException 查询失败
     */
    @RequestMapping("/getFreeTableTotal.do")
    @ResponseBody
    @RequiresRoles("学生")
    public Result getFreeTableTotal(@RequestBody GetTableVo vo) throws LibraryException, NothingException {
        if (vo.getFloor().equals("1")) {
            throw new NothingException("当前楼层 1 层无座位");
        }
        Integer total = libraryService.getFreeTableTotal(vo);
        return new Result().setCode(200).setMessage("获取可用桌位总数成功").setData(total);
    }

    /**
     * 占用座位：订单为已预定状态
     *
     * @param map 占用作为信息
     * @return 成功提示
     * @throws LibraryException 占用失败
     */
    @RequestMapping("/toGrabSeat.do")
    @ResponseBody
    @RequiresRoles("学生")
    public Result toGrabSeat(@RequestBody Map map) throws LibraryException {
        String tableId = (String) map.get("tableId");
        String date = (String) map.get("date");
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String stuId = userService.getStudentIdByUserId(userId);
        libraryService.toGrabSeat(tableId, stuId, date);

        return new Result().setCode(200).setMessage("座位预定成功");
    }

}
