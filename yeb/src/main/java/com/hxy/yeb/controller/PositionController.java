package com.hxy.yeb.controller;

import com.hxy.yeb.common.entity.AxiosResult;
import com.hxy.yeb.domain.Position;
import com.hxy.yeb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = {"/position/delete/{ids}"})
    @ResponseBody
    public AxiosResult deletePositions(@PathVariable("ids") List<Integer> ids){
        try {
            int n = positionService.deletePositionsByIds(ids);
            return AxiosResult.success();
        } catch (Exception e){
            return AxiosResult.error();
        }
    }

}
