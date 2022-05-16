package com.djtu.dictionary.web.controller;

import com.djtu.dictionary.service.DicTypeService;
import com.djtu.dictionary.service.DicValueService;
import com.djtu.dictionary.pojo.DicType;
import com.djtu.dictionary.pojo.DicValue;
import com.djtu.dictionary.pojo.vo.DicTypeVo;
import com.djtu.dictionary.pojo.vo.DicValueVo;
import com.djtu.exception.DictionaryException;
import com.djtu.exception.NothingException;
import com.djtu.redis.RedisService;
import com.djtu.response.Result;
import com.djtu.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dic")
public class DictionaryController {

    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private DicTypeService dicTypeService;
    @Autowired
    private RedisService redisService;

    /**
     * 获取学院列表
     *
     * @return 学院列表
     */
    @RequestMapping("/getCollegeList.do")
    @ResponseBody
    public Result getCollegeList() throws JsonProcessingException {
        String collegeList = redisService.get("collegeList");
        if (collegeList == null) {
            synchronized (this) {
                if (collegeList == null) {
                    List<DicValue> list = dicValueService.getCollegeList();
                    redisService.set("collegeList", new ObjectMapper().writeValueAsString(list));
                    return new Result().setCode(200).setMessage("获取成功").setData(list);
                }
            }
        }
        return new Result().setCode(200).setMessage("从缓存服务器获取列表成功").setData(new ObjectMapper().readValue(collegeList, List.class));
    }

    /**
     * 获取楼宇类型列表
     *
     * @return 楼宇类型列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getBuildingTypeList.do")
    @ResponseBody
    public Result getBuildingList() throws NothingException, JsonProcessingException {
        String buildingTypeList = redisService.get("buildingTypeList");
        if (buildingTypeList == null) {
            synchronized (this) {
                if (buildingTypeList == null) {
                    List<DicValue> list = dicValueService.getBuildingTypeList();
                    redisService.set("buildingTypeList", new ObjectMapper().writeValueAsString(list));
                    return new Result().setCode(200).setMessage("获取成功").setData(list);
                }
            }
        }
        return new Result().setCode(200).setMessage("从缓存服务器获取列表成功").setData(new ObjectMapper().readValue(buildingTypeList, List.class));
    }

    /**
     * 获取所有数据字典值
     *
     * @return 数据字典值列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDicVL.do")
    @ResponseBody
    public Result getDicValuesList() throws NothingException {
        List<DicValue> list = dicValueService.getDicValuesList();
        return new Result().setCode(200).setMessage("获取成功").setData(list);
    }

    /**
     * 模糊、分页查询数据字典值
     *
     * @param dicValueVo 封装了模糊查询数据字典值和分页数据的vo类
     * @return 查询到的数据以及数据总量
     * @throws DictionaryException 查询失败时抛出的异常
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDicVByCV.do")
    @ResponseBody
    public Result getDicValuesAndTotal(@RequestBody DicValueVo dicValueVo) throws NothingException {
        List<DicValue> list = dicValueService.getDicValuesByCodeOrValue(dicValueVo);
        Integer total = dicValueService.getDicValuesListNum();
        Map<String, Object> map = new Hashtable<>();
        map.put("list", list);
        map.put("total", total);
        return new Result().setCode(200).setMessage("获取成功").setData(map);
    }

    /**
     * 添加数据字典值
     *
     * @param dicValue 数据字典值
     * @return 是否添加成功返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping(value = "/setDicV.do", method = RequestMethod.POST)
    @ResponseBody
    public Result setDicValues(@RequestBody DicValue dicValue) throws DictionaryException {
        dicValue.setId(StringUtil.generateUUID());
        dicValueService.setDicValues(dicValue);
        redisService.delete("buildingTypeList");
        redisService.delete("collegeList");
        return new Result().setCode(200).setMessage("添加成功");
    }

    /**
     * 删除数据字典值
     *
     * @param data 数据字典值id列表
     * @return 是否删除成功返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping(value = "/delDicV.do")
    @ResponseBody
    public Result delDicValues(@RequestBody List<String> data) throws DictionaryException {
        dicValueService.delDicValues(data);
        redisService.delete("buildingTypeList");
        redisService.delete("collegeList");
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 更新数据字典信息
     *
     * @param dicValue 数据字典值实例
     * @return 是否修改成功返回信息
     */
    @RequiresRoles("管理员")
    @RequestMapping("/updateDicV.do")
    @ResponseBody
    public Result updateDicValues(@RequestBody DicValue dicValue) throws DictionaryException {
        dicValueService.updateDicValues(dicValue);
        redisService.delete("buildingTypeList");
        redisService.delete("collegeList");
        return new Result().setCode(200).setMessage("修改成功");
    }

    /**
     * 查询所有数据字典类型
     *
     * @return 数据字典类型列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDicTL.do")
    @ResponseBody
    public Result getDicTypeList() throws NothingException {
        List<DicType> list = dicTypeService.getDicTypeList();
        return new Result().setCode(200).setMessage("查询成功").setData(list);
    }

    /**
     * 查询所有数据字典类型,可根据code或name查询也可查询所有
     *
     * @return 数据字典类型列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDicTByCN.do")
    @ResponseBody
    public Result getDicTypeListByCodeOrName(@RequestBody DicTypeVo dicTypeVo) throws NothingException {
        List<DicType> list = dicTypeService.getDicTypeListByCodeOrName(dicTypeVo);
        Integer total = dicTypeService.getDicTypeListNum();
        Map<String, Object> map = new Hashtable<>();
        map.put("list", list);
        map.put("total", total);
        return new Result().setCode(200).setMessage("条件查询成功").setData(map);
    }

    /**
     * 插入数据字典类型
     *
     * @param dicType 数据字典类型实例
     * @return 是否插入信息
     * @throws DictionaryException 插入数据字典类型失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/setDicTL.do")
    @ResponseBody
    public Result setDicType(@RequestBody DicType dicType) throws DictionaryException {
        dicType.setId(StringUtil.generateUUID());
        dicTypeService.setDicType(dicType);
        return new Result().setCode(200).setMessage("插入成功");
    }

    /**
     * 删除数据字典类型
     *
     * @param data 数据字典类型id列表
     * @return 是否删除数据字典信息
     * @throws DictionaryException 删除数据字典类型失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/delDicTL.do")
    @ResponseBody
    public Result delDicType(@RequestBody List<String> data) throws DictionaryException {
        dicTypeService.delDicType(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 更新数据字典类型
     *
     * @param dicType 数据字典类型
     * @return 是否更新数据字典信息
     * @throws DictionaryException 更新数据字典类型失败
     */
    @RequiresRoles("管理员")
    @RequestMapping(value = "/updateDicTL.do")
    @ResponseBody
    public Result updateDicType(@RequestBody DicType dicType) throws DictionaryException {
        dicTypeService.updateDicType(dicType);
        return new Result().setCode(200).setMessage("更新成功");
    }

}
