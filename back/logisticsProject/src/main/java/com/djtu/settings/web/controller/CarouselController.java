package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 走马灯图片相关接口
 */

@Controller
@PropertySource({"classpath:server.properties"})
public class CarouselController {

    //文件绝对路径前置目录
    @Value("${CAROUSEL_ABSOLUTE_PATH}")
    private String ABSOLUTE_PATH;
    //远程图片服务器前置目录
    @Value("${IMAGE_SERVER_PATH}")
    private String SERVER_PATH;

    /**
     * 上传走马灯图片接口
     *
     * @param file 上传的图片
     * @return 仅用于让前端识别为请求成功
     */
    @RequestMapping("/addCarousel.do")
    @ResponseBody
    public String addCarousel(MultipartFile file) {
        try {
            file.transferTo(new File(ABSOLUTE_PATH + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除走马灯图片的接口
     *
     * @param name 要删除的文件名
     * @return 仅用于让前端识别为请求成功
     */
    @RequiresRoles("管理员")
    @RequestMapping("/removeCarousel.do")
    @ResponseBody
    public Result removeCarousel(String name) {
        File file = new File(ABSOLUTE_PATH + name);
        if (file.delete()) {
            return new Result().setCode(200).setMessage("删除图片成功");
        }
        return new Result().setCode(402).setMessage("删除图片失败");
    }

    /**
     * 获取当前所有走马灯图片列表
     *
     * @return 当前所有图片列表
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getFileList.do")
    @ResponseBody
    public Result getFileList() {
        List<Map<String, String>> list = new ArrayList<>();
        File file = new File(ABSOLUTE_PATH);
        File[] files = file.listFiles();
        if (files == null) {
            return new Result().setCode(402).setMessage("获取走马灯图片列表失败");
        }
        for (File f : files) {
            Map<String, String> map = new HashMap<>();
            map.put("name", f.getName());
            map.put("url", SERVER_PATH + f.getName());
            list.add(map);
        }
        return new Result().setCode(200).setMessage("获取走马灯图片列表成功").setData(list);
    }

}
