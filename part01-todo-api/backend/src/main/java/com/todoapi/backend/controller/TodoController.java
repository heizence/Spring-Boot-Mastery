package com.todoapi.backend.controller;

import com.todoapi.backend.dto.RequestDto;
import com.todoapi.backend.dto.ResponseDto;
import com.todoapi.backend.entity.Todo;
import com.todoapi.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor // Use constructor injection
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseDto<List<Todo>> getAllTodos() {
        log.info("start getAllTodos");
        return ResponseDto.success(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseDto<Todo> addTodo(@RequestBody RequestDto.Add dto) {
        log.info("start addTodo");
        log.debug("dto : " + dto);
        Todo newTodo = todoService.addTodo(dto);
        log.info("addTodo done.");
        return ResponseDto.success(newTodo);
    }


    @PutMapping("/{id}") // Use PUT for full updates, specify ID in path
    public ResponseDto<Void> editTodo(
            @PathVariable Long id,
            @RequestBody RequestDto.Edit dto) {
        log.info("start editTodo");
        log.debug("id : " + id);
        log.debug("dto title : " + dto.getTitle());
        log.debug("dto description : " + dto.getDescription());
        todoService.editTodo(id, dto);
        log.info("editTodo done.");
        return ResponseDto.success();
    }

    @PatchMapping("/{id}/toggle") // Use PATCH for partial updates like toggling
    public ResponseDto<Void> toggleTodo(@PathVariable Long id) {
        log.info("start toggleTodo");
        log.debug("id : " + id);
        todoService.toggleTodo(id);
        log.info("toggleTodo done.");
        return ResponseDto.success();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseDto<Void> deleteTodo(@PathVariable Long id) {
        log.info("start deleteTodo");
        log.debug("id : " + id);
        todoService.deleteTodo(id);
        log.info("deleteTodo done.");
        return ResponseDto.success();
    }
}
