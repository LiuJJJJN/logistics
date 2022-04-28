package com.djtu.settings.web.controller;

import com.djtu.exception.DictionaryException;
import com.djtu.exception.UserManagerException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.TutorVo;
import com.djtu.settings.service.UserManageService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class UserManageController {

    @Autowired
    UserManageService userManageService;

    /**
     * 获取导员信息
     * @param tutorVo 导员实例
     * @return 导员信息列表
     * @throws UserManagerException
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/getTutorList.do")
    @ResponseBody
    public Result getTutorList(@RequestBody TutorVo tutorVo)throws UserManagerException {
        List<Tutor> list=userManageService.getTutorList(tutorVo);
        Integer num=userManageService.gitTutorNum();
        Map<String,Object> map=new Hashtable<>();
        map.put("total",num);
        map.put("list",list);
        return new Result().setCode(200).setMessage("查询成功").setData(map);
    }

    /**
     * 获取college列表
     * @return 学院列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/getCollegeList.do")
    @ResponseBody
    public Result getCollegeList(){
        List<DicValue> list=userManageService.getCollegeList();
        return new Result().setCode(200).setMessage("查询成功").setData(list);
    }

    /**
     * 批量重置导员密码
     * @param data
     * @return
     * @throws UserManagerException
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/resetTP.do")
    @ResponseBody
    public Result resetTutorPwd(@RequestBody List<String> data) throws UserManagerException{
        userManageService.resetTutorPwd(data);
        return new Result().setCode(200).setMessage("重置成功");
    }

    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/delTutorL.do")
    @ResponseBody
    public Result delTutorList(@RequestBody List<String> data)throws UserManagerException{
        System.out.println("!!!!"+data);
        userManageService.delTutorList(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/addOrUpTutorRemark.do")
    @ResponseBody
    public Result addOrUpTutorRemark(@RequestBody Tutor tutor) throws UserManagerException{
        userManageService.addOrUpTutorRemark(tutor);
        return new Result().setCode(200).setMessage("添加/修改成功");
    }

}
