package com.uca.pncsegundoparcialgestiontareas.services;


import com.uca.pncsegundoparcialgestiontareas.dto.request.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.dto.response.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.entities.Task;
import com.uca.pncsegundoparcialgestiontareas.exceptions.TaskNotFound;
import com.uca.pncsegundoparcialgestiontareas.repository.TaskRepository;
import com.uca.pncsegundoparcialgestiontareas.utils.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;


    public void createTask(TaskDTORequest task){
        if (taskRepository.existsByTitle((task.title().toLowerCase()))){
            throw new IllegalArgumentException("Task with title " + task.title() + " already exists");
        }

        taskRepository.save(TaskMapper.toEntity(task));
    }

    public TaskDTOResponse findTaskById(long id){
        return TaskMapper.toResponse(taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFound("Task not found with id " + id)
        ));
    }

    public void deleteTaskById(long id){
        taskRepository.deleteById(id);
    }

    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }

    public void updateTask(long id, TaskDTORequest task){
        Task taskToUpdate = TaskMapper.toEntity(task);
        if (taskRepository.existsById(id)){
            taskToUpdate.setId(id);
        }else{
            throw new TaskNotFound("Task not found with id " + id);
        }
        taskRepository.save(taskToUpdate);
    }

}
