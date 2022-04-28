package com.djtu.settings.web.controller;

import com.djtu.exception.DictionaryException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.DicType;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.vo.DicTypeVo;
import com.djtu.settings.pojo.vo.DicValueVo;
import com.djtu.settings.service.DicTypeService;
import com.djtu.settings.service.DicValueService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @RequiresRoles("管理员")
    @RequestMapping("/getDicVL.do")
    @ResponseBody
    public Result getDicValuesList() throws DictionaryException {
        List<DicValue> list=dicValueService.getDicValuesList();
        return new Result().setCode(200).setMessage("获取成功").setData(list);
    }

    /**
     * 查询所有数据字典值，可根据value或type_code查询也可查询所有
     * @param dicValueVo
     * @return
     * @throws DictionaryException
     */
    @RequestMapping("/getDicVByCV.do")
    @ResponseBody
    public Result getDicValuesByCodeOrValue(@RequestBody DicValueVo dicValueVo) throws DictionaryException{
        List<DicValue> list=dicValueService.getDicValuesByCodeOrValue(dicValueVo);
        Integer total=dicValueService.getDicValuesListNum();
        Map<String,Object> map=new Hashtable<>();
        map.put("list",list);
        map.put("total",total);
        return new Result().setCode(200).setMessage("获取成功").setData(map);
    }

    /**
     *管理员-添加数据字典信息
     * @param dicValue
     * @return 是否添加返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping(value = "/setDicV.do",method = RequestMethod.POST)
    @ResponseBody
    public Result setDicValues(@RequestBody DicValue dicValue) throws DictionaryException{
        System.out.println("!!!!"+dicValue);
        dicValue.setId(StringUtil.generateUUID());
        dicValueService.setDicValues(dicValue);
        return new Result().setCode(200).setMessage("添加成功");
    }

    /**
     * 管理员-删除数据字典信息
     * @param data id集合
     * @return 是否删除返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping(value = "/delDicV.do")
    @ResponseBody
    public Result delDicValues(@RequestBody List<String> data) throws DictionaryException{
        dicValueService.delDicValues(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 管理员-更新数据字典信息
     * @param dicValue 数据字典值实例
     * @return 是否修改返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping("/updateDicV.do")
    @ResponseBody
    public Result updateDicValues(@RequestBody DicValue dicValue)throws DictionaryException{
        dicValueService.updateDicValues(dicValue);
        return new Result().setCode(200).setMessage("修改成功");
    }

    /**
     * 查询所有数据字典类型
     * @return 数据字典类型列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDicTL.do")
    @ResponseBody
    public Result getDicTypeList() throws DictionaryException{
        List<DicType> list=dicTypeService.getDicTypeList();
        return new Result().setCode(200).setMessage("查询成功").setData(list);
    }

    /**
     * 查询所有数据字典类型,可根据code或name查询也可查询所有
     * @return 数据字典类型列表
     */
    @RequestMapping("/getDicTByCN.do")
    @ResponseBody
    public Result getDicTypeListByCodeOrName(@RequestBody DicTypeVo dicTypeVo) throws DictionaryException{
        List<DicType> list=dicTypeService.getDicTypeListByCodeOrName(dicTypeVo);
        Integer total=dicTypeService.getDicTypeListNum();
        Map<String,Object> map=new Hashtable<>();
        map.put("list",list);
        map.put("total",total);
        return new Result().setCode(200).setMessage("条件查询成功").setData(map);
    }

    @RequiresRoles("管理员")
    @RequestMapping("/setDicTL.do")
    @ResponseBody
    public Result setDicType(@RequestBody DicType dicType) throws DictionaryException{
        dicType.setId(StringUtil.generateUUID());
        dicTypeService.setDicType(dicType);
        return new Result().setCode(200).setMessage("插入成功");
    }

    @RequiresRoles("管理员")
    @RequestMapping("/delDicTL.do")
    @ResponseBody
    public Result delDicType(@RequestBody List<String> data) throws DictionaryException{
       dicTypeService.delDicType(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    @RequiresRoles("管理员")
    @RequestMapping(value = "/updateDicTL.do")
    @ResponseBody
    public Result updateDicType(@RequestBody DicType dicType) throws DictionaryException{
        dicTypeService.updateDicType(dicType);
        return new Result().setCode(200).setMessage("更新成功");
    }

}
