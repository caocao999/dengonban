package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
	 public List<Message> findAllByOrderByIdDesc();
}
