package com.djtu.settings.web.controller;

import com.djtu.exception.DictionaryException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.DicType;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.vo.DicTypeVo;
import com.djtu.settings.service.DicTypeService;
import com.djtu.settings.service.DicValueService;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class DictionaryController {

    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private DicTypeService dicTypeService;
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

    /**
     * 查询所有数据字典类型
     * @return 数据字典类型列表
     */
    @RequestMapping("/getDicTL.do")
    @ResponseBody
    public Result getDicTypeList() throws DictionaryException{
        List<DicType> list=dicTypeService.getDicTypeList();
        return new Result().setCode(200).setMessage("查询成功").setData(list);
    }

    @RequestMapping("/getDicTByCN.do")
    @ResponseBody
    public Result getDicTypeListByCodeOrName(@RequestBody DicTypeVo dicTypeVo){
        List<DicType> list=dicTypeService.getDicTypeListByCodeOrName(dicTypeVo);
        Integer total=list.size();
        Map<String,Object> map=new Hashtable<>();
        map.put("list",list);
        map.put("total",total);
        return new Result().setCode(200).setMessage("条件查询成功").setData(map);
    }

    @RequestMapping("/setDicTL.do")
    @ResponseBody
    public Result setDicType(@RequestBody DicType dicType) throws DictionaryException{
        dicType.setId(StringUtil.generateUUID());
        dicTypeService.setDicType(dicType);
        return new Result().setCode(200).setMessage("插入成功");
    }

    @RequestMapping("/delDicTL.do")
    @ResponseBody
    public Result delDicType(String code) throws DictionaryException{
        dicTypeService.delDicType(code);
        return new Result().setCode(200).setMessage("删除成功");
    }

    @RequestMapping(value = "/updateDicTL.do")
    @ResponseBody
    public Result updateDicType(@RequestBody DicType dicType) throws DictionaryException{
        dicTypeService.updateDicType(dicType);
        return new Result().setCode(200).setMessage("更新成功");
    }
}
