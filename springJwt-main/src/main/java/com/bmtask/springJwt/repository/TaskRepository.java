package com.bmtask.springJwt.repository;

import com.bmtask.springJwt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByNameContainingIgnoreCase(String keyword);
    List<Task> findByDescriptionContainingIgnoreCase(String keyword);
    List<Task> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameKeyword, String descriptionKeyword);

//    List<Task> findByNameAndDescriptionAndTitle


}
