package com.nd.controller;

//通话日志控制层

import com.nd.entity.ContAmount;
import com.nd.entity.ContLog;
import com.nd.entity.OccuAmountLog;
import com.nd.entity.OccuAmountLog2;
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

    //查询页面
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

        List<ContAmount> contAmount =contLogService.findContAmount(candidate, contDate);
        System.out.println("!contAmounts:"+contAmount);
        model.addAttribute("contAmounts",contAmount);
        return "view";
    }

    //展示职业记录
    @RequestMapping("occupationAmount.do")
    public String occupationAmount(Model model){
        List<OccuAmountLog> ContOccuAmount =contLogService.findOccuAmount();
        System.out.println("!ContOccuAmount:"+ContOccuAmount);
        model.addAttribute("contOccuAmounts",ContOccuAmount);
        return "occupationAmount";
    }

    //展示职业记录2
    @RequestMapping("oA2.do")
    public String oA2(Model model){
        List<OccuAmountLog2> ContOccuAmount2 =contLogService.findOccuAmount2();
        System.out.println("!ContOccuAmount:"+ContOccuAmount2);
        model.addAttribute("contOccuAmounts",ContOccuAmount2);
        return "oA2";
    }


    //页脚
    @RequestMapping("footer.do")
    public String footer(){
        return "footer";
    }

    //test
    @RequestMapping("test.do")
    public String test(){

//        List<ContAmount> contAmount =contLogService.findContAmount("Romney, Mitt", "2011-11-05");
//        System.out.println("!contAmounts:"+contAmount);
//        model1.addAttribute("contAmounts",contAmount);
        return "test";
    }

    //跳转
    @RequestMapping("jump.do")
    public String jump(){
        return "jump";
    }
}
