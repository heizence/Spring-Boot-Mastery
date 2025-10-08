package com.example.todoListApi.service;

import com.example.todoListApi.dto.RequestDto;
import com.example.todoListApi.entity.Todo;
import com.example.todoListApi.exception.CommonException;
import com.example.todoListApi.exception.ErrorCode;
import com.example.todoListApi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    // TodoService needs a TodoRepository to function. This is a dependency.
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

        String title = dto.getTitle();
        String desc = dto.getDescription();

        if (title == null | desc == null) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.TODO_NOT_FOUND));

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        log.debug("[TodoService]edited todo : " + todo);
        todoRepository.save(todo);
    }

    public void toggleTodo(Long id) {
        log.info("[TodoService]start toggleTodo");
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.TODO_NOT_FOUND));

        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
        log.debug("[TodoService]toggled todo : " + todo);
    }

    public void deleteTodo(Long id) {
        log.info("[TodoService]start deleteTodo");
        todoRepository.deleteById(id);
    }
}
