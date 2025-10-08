package com.example.todoListApi.repository;

import com.example.todoListApi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// By extending JpaRepository<Todo, Long>, we get methods like save(), findById(), findAll(), deleteById() for free.
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
