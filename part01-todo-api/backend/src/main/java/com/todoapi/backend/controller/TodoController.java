package com.todoapi.backend.controller;

import com.todoapi.backend.dto.RequestDto;
import com.todoapi.backend.dto.ResponseDto;
import com.todoapi.backend.entity.Todo;
import com.todoapi.backend.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor // Use constructor injection
@Tag(name = "Todo API", description = "API for managing Todos") // Adds a tag to group these APIs in Swagger UI.
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "Get all Todos", description = "Fetches a list of all available todo items."
    )
    @GetMapping // Describes the endpoint.
    public ResponseDto<List<Todo>> getAllTodos() {
        log.info("start getAllTodos");
        return ResponseDto.success(todoService.getAllTodos());
    }

    @Operation(summary = "Add a new Todo", description = "Creates a new todo item and saves it to the database.")
    @PostMapping
    public ResponseDto<Todo> addTodo(@RequestBody RequestDto.Add dto) {
        log.info("start addTodo");
        log.debug("dto : " + dto);
        Todo newTodo = todoService.addTodo(dto);
        log.info("addTodo done.");
        return ResponseDto.success(newTodo);
    }

    @Operation(summary = "Edit an existing Todo", description = "Updates the title and description of an existing todo item.")
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

    @Operation(summary = "Toggle a Todo's completion status", description = "Marks a todo item as completed or not completed.")
    @PatchMapping("/{id}/toggle") // Use PATCH for partial updates like toggling
    public ResponseDto<Void> toggleTodo(@PathVariable Long id) {
        log.info("start toggleTodo");
        log.debug("id : " + id);
        todoService.toggleTodo(id);
        log.info("toggleTodo done.");
        return ResponseDto.success();
    }

    @Operation(summary = "Delete a Todo", description = "Deletes a todo item from the database by its ID.")
    @DeleteMapping("/{id}/delete")
    public ResponseDto<Void> deleteTodo(@PathVariable Long id) {
        log.info("start deleteTodo");
        log.debug("id : " + id);
        todoService.deleteTodo(id);
        log.info("deleteTodo done.");
        return ResponseDto.success();
    }
}
