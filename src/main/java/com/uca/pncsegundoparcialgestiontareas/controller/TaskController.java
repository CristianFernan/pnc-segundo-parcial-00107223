package com.uca.pncsegundoparcialgestiontareas.controller;

import com.uca.pncsegundoparcialgestiontareas.dto.GeneralResponse;
import com.uca.pncsegundoparcialgestiontareas.dto.request.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<GeneralResponse> findAll() {
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(taskService.findAllTask())
                .message("All tasks found")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(taskService.findTaskById(id))
                .message("Task found with id: " + id)
                .build());
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createTask(@Valid @RequestBody TaskDTORequest task) {
        taskService.createTask(task);
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(task)
                .message("Task has been created")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateTask(@PathVariable long id, @Valid @RequestBody TaskDTORequest task) {
        taskService.updateTask(id, task);
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(task)
                .message("Task has been updated")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteTask(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(taskService.findTaskById(id))
                .message("Task has been deleted")
                .build());
    }
}