package com.Mayank.calculator.service;


import com.Mayank.calculator.entity.UserHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserHistoryService {

    void save(UserHistory userHistory);
    UserHistory findById(int id);

    List<UserHistory> findAll();

    void deleteById(int id);
}
