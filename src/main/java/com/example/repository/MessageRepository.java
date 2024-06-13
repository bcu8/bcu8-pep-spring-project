package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    Message findById(int id);
    Message save(Message message);
    List<Message> findAll();
    void deleteById(int id);
    List<Message> findByPostedBy(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :id")
    int updateMessageTextById(int id, String messageText);
}
