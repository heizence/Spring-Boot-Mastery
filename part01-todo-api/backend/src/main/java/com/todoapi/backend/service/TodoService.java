package com.todoapi.backend.service;

import com.todoapi.backend.dto.RequestDto;
import com.todoapi.backend.entity.Todo;
import com.todoapi.backend.exception.CommonException;
import com.todoapi.backend.exception.ErrorCode;
import com.todoapi.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        log.info("[TodoService]start getAllTodos");
        log.debug("[TodoService]all todos : " + todoRepository.findAll());
        return todoRepository.findAll();
    }

    public Todo addTodo(RequestDto.Add dto) {
        log.info("[TodoService]start addTodo");

        String title = dto.getTitle();
        String desc = dto.getDescription();
        if (title == null || title.isBlank() || desc == null || desc.isBlank()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        log.debug("[TodoService]new todo : " + todo);
        return todoRepository.save(todo);
    }

    public void editTodo(Long id, RequestDto.Edit dto) {
        log.info("[TodoService]start editTodo");
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo == null) {
            throw new CommonException(ErrorCode.TODO_NOT_FOUND);
        }

        String title = dto.getTitle();
        String desc = dto.getDescription();

        if (title == null | desc == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        log.debug("[TodoService]edited todo : " + todo);
        todoRepository.save(todo);
    }

    public void toggleTodo(Long id) {
        log.info("[TodoService]start toggleTodo");
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo == null) {
            throw new CommonException(ErrorCode.TODO_NOT_FOUND);
        }

        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
        log.debug("[TodoService]toggled todo : " + todo);
    }

    public void deleteTodo(Long id) {
        log.info("[TodoService]start deleteTodo");
        todoRepository.deleteById(id);
    }
}
