package com.bmtask.springJwt.service;


import com.bmtask.springJwt.model.Task;
import com.bmtask.springJwt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> searchTasksByName(String keyword) {
        return taskRepository.findByNameContainingIgnoreCase(keyword);
    }
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public List<Task> searchTasksByDescription(String keyword) {
        return taskRepository.findByDescriptionContainingIgnoreCase(keyword);
    }
    public List<Task> searchTasksByNameOrDescription(String nameKeyword, String descriptionKeyword) {
        return taskRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(nameKeyword, descriptionKeyword);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Integer id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            Task savedTask = taskRepository.save(task);

            // Send email notification
//            emailService.sendSimpleMessage("recipient@example.com", "Task Updated", "Task has been updated: " + savedTask.getName());

            return savedTask;
        }
        return null;
    }

}
