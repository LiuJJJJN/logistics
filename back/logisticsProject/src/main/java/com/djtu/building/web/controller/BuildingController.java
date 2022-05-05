package com.djtu.building.web.controller;

import com.djtu.building.pojo.Building;
import com.djtu.response.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/building")
public class BuildingController {

    @RequestMapping("/addBuilding.do")
    @ResponseBody
    public Result addBuilding(@RequestBody Building building){
        System.out.println(building);

        return null;
    }

}
