package com.bmtask.springJwt.controller;

import com.bmtask.springJwt.model.Task;
import com.bmtask.springJwt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private TaskService taskService;



    @GetMapping("/searchByName")
    public List<Task> searchTaskByName(@RequestParam String keyword )
    {
        return taskService.searchTasksByName(keyword);
    }

    @GetMapping("/searchByDescription")
    public List<Task> searchTaskByDescription(@RequestParam String keyword ) {
        return taskService.searchTasksByDescription(keyword);
    }
    @GetMapping("/searchByNameAndDescription")
    public List<Task> searchTasks(@RequestParam String keyword ,String descriptionKeyword) {
        return taskService.searchTasksByNameOrDescription(keyword,descriptionKeyword);
    }
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }


    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/readTask/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/readTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok().body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteTask/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("updateTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task != null) {
            return ResponseEntity.ok().body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }
}
