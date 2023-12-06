package com.Mayank.calculator.service;

import com.Mayank.calculator.dao.UserHistoryDao;
import com.Mayank.calculator.entity.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserHistoryServiceImplementation implements UserHistoryService {
    public UserHistoryDao userHistoryDao;


    @Autowired
    public UserHistoryServiceImplementation(UserHistoryDao userHistoryDao){
        this.userHistoryDao = userHistoryDao;
    }


    @Override
    public void save(UserHistory userHistory) {
        userHistoryDao.save(userHistory);
    }

    @Override
    public UserHistory findById(int id) {
        Optional<UserHistory> userHistory = userHistoryDao.findById(id);
        return userHistory.orElseThrow();
    }

    @Override
    public List<UserHistory> findAll() {
        return userHistoryDao.findAll();
    }

    @Override
    public void deleteById(int id) {
        userHistoryDao.deleteById(id);
    }
}
