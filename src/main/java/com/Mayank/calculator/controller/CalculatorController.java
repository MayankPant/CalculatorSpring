package com.Mayank.calculator.controller;

import com.Mayank.calculator.entity.UserHistory;
import com.Mayank.calculator.service.UserHistoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(userHistory);
        userHistory.setExpression(userHistory.getResult());
        model.addAttribute("userhistory", userHistory);

        return "index";
    }

    @GetMapping("/history")
    public String viewHistory(Model model){
        List<UserHistory> histories = userHistoryService.findAll();
        model.addAttribute("userHistory", histories);
        return "user-history";
    }

    @GetMapping("/delete")
    public String deleteHistory(@RequestParam("historyId") int id, Model model){
        userHistoryService.deleteById(id);
        return "redirect:/calculator/history";
    }
    @PostMapping("/calculateAndSave")
    public String calculateAndSave(@ModelAttribute("userhistory") UserHistory userHistory, Model model){
            String result = userHistoryService.calculateResult(userHistory.getExpression());
            userHistory.setResult(result);
            userHistoryService.save(userHistory);
            userHistory.setExpression(result);
            model.addAttribute("userhistory", userHistory);
            return "index";
    }

    @GetMapping("/clearAll")
    public String clearAll(){
        userHistoryService.deleteAll();

        return "user-history";
    }
}
