package com.Mayank.calculator.controller;

import com.Mayank.calculator.entity.UserHistory;
import com.Mayank.calculator.service.UserHistoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private UserHistoryServiceImplementation userHistoryService;

    @Autowired
    public CalculatorController(UserHistoryServiceImplementation userHistoryService){
        this.userHistoryService = userHistoryService;
    }
    @GetMapping("/index")
    public String openApp(Model model){
        UserHistory userHistory = new UserHistory();
        model.addAttribute("userhistory", userHistory);
        return "index";
    }

    @GetMapping("/insert")
    public String insertHistory(@RequestParam("historyId") int historyId, Model model){
        UserHistory userHistory = userHistoryService.findById(historyId);
        model.addAttribute("userHistory", userHistory);

        return "index";
    }

    @GetMapping("/history")
    public String viewHistory(Model model){
        List<UserHistory> histories = userHistoryService.findAll();
        model.addAttribute("userHistory", histories);
        return "user-history";
    }
}
