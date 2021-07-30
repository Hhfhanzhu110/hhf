package com.exm.demo.yeb.position.controller;

import com.exm.demo.entity.AxiosResult;
import com.exm.demo.yeb.position.domain.Position;
import com.exm.demo.yeb.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    PositionService positionService;

    @RequestMapping("/position/getPositionList")
    @ResponseBody
    public AxiosResult getPositionList(){
       List<Position>  positions = positionService.getPositionList();
       return AxiosResult.success(positions);
    }

    @RequestMapping("/position/addPosition")
    @ResponseBody
    public AxiosResult addPosition(Position position){
        String msg = positionService.addPosition(position);
        if (msg.equals("ok")){
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return AxiosResult.success(msg);
    }

    @RequestMapping(value = {"/position/{id}"},method = {RequestMethod.DELETE})
    @ResponseBody
    public AxiosResult deletePosition(@PathVariable("id") String id){
        try {
            int n = positionService.deletePosition(id);
            return AxiosResult.success();
        } catch (Exception e){
            return AxiosResult.error();
        }
    }

    @RequestMapping(value = {"/position/put"})
    @ResponseBody
    public AxiosResult putPosition(Position position){
        try {
            int n = positionService.putPosition(position);
            return AxiosResult.success();
        } catch (Exception e){
            return AxiosResult.error();
        }
    }

}
