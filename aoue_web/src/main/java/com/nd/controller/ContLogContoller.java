package com.nd.controller;

//通话日志控制层

import com.nd.entity.ContLog;
import com.nd.service.ContLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/contribute/")
public class ContLogContoller {
    @Autowired//控制层调用业务层
    private ContLogService contLogService;
    @RequestMapping("query.do")
    public String query(){
        return "query";
    }

    //展示献金记录
    @RequestMapping("view.do")
    public String view(Model model,
                       @RequestParam(name = "candidate",defaultValue = "")String candidate,
                       @RequestParam(name = "contDate",defaultValue = "")String contDate){
        System.out.println("!candidate:"+candidate+",contDate="+contDate+"!");
        List<ContLog> contLog = contLogService.findContLog(candidate, contDate);
        System.out.println("!contLogs:"+contLog);
        model.addAttribute("contLogs",contLog);

        return "view";
    }

}
