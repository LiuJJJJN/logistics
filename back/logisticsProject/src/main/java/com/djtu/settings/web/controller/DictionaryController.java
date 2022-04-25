package com.djtu.settings.web.controller;

import com.djtu.exception.DictionaryException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DictionaryController {

    @Autowired
    private DicValueService dicValueService;

    /**
     *管理员-加载所有数据字典信息
     * @return 返回数据字典列表信息
     */
    @RequestMapping("/getDicVL.do")
    @ResponseBody
    public Result getDicValuesList() throws DictionaryException {
        List<DicValue> list=dicValueService.getDicValuesList();
        return new Result().setCode(200).setMessage("获取成功").setData(list);
    }

    /**
     *管理员-添加数据字典信息
     * @param dicValue
     * @return 是否添加返回信息
     */
    @RequestMapping(value = "/setDicV.do",method = RequestMethod.POST)
    @ResponseBody
    public Result setDicValues(@RequestBody DicValue dicValue) throws DictionaryException{
        dicValueService.setDicValues(dicValue);
        return new Result().setCode(200).setMessage("添加成功");
    }

    /**
     * 管理员-删除数据字典信息
     * @param id 主键
     * @return 是否删除返回信息
     */
    @RequestMapping(value = "/delDicV.do")
    @ResponseBody
    public Result delDicValues(String id) throws DictionaryException{
        dicValueService.delDicValues(id);
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 管理员-更新数据字典信息
     * @param value 更新后的值
     * @param id 主键
     * @return
     */
    @RequestMapping("/updateDicV.do")
    @ResponseBody
    public Result updateDicValues(@RequestParam(value="value")String value,@RequestParam(value="id")String id)throws DictionaryException{
        dicValueService.updateDicValues(value,id);
        return new Result().setCode(200).setMessage("修改成功");
    }
}
