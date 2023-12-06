package com.Mayank.calculator.dao;

import com.Mayank.calculator.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryDao extends JpaRepository<UserHistory, Integer> {
}
