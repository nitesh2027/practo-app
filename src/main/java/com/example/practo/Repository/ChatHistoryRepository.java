package com.example.practo.Repository;

import com.example.practo.Entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository
        extends JpaRepository<ChatHistory, Long> {

}