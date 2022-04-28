package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private DicValueService dicValueService;

    /**
     * 获取学院列表
     * @return 学院列表
     */
    @RequestMapping("/getCollegeList.do")
    @ResponseBody
    public Result getCollegeList() {
        List<DicValue> list = dicValueService.getCollegeList();
        return new Result().setCode(200).setMessage("获取成功").setData(list);
    }

}
