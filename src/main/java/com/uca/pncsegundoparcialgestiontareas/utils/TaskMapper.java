package com.uca.pncsegundoparcialgestiontareas.utils;

import com.uca.pncsegundoparcialgestiontareas.dto.request.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.dto.response.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.entities.Task;

public class TaskMapper {
    public static Task toEntity(TaskDTORequest taskDTORequest){
        return Task.builder()
                .title(taskDTORequest.title().toLowerCase())
                .description(taskDTORequest.description())
                .status(taskDTORequest.status())
                .priority(taskDTORequest.priority())
                .estimatedHours(taskDTORequest.estimatedHours())
                .loggedHours(taskDTORequest.loggedHours())
                .dueDate(taskDTORequest.dueDate())
                .assignedTo(taskDTORequest.assignedTo())
                .active(taskDTORequest.active())
                .build();
    }

    public static TaskDTOResponse toResponse(Task task){
        return new TaskDTOResponse(
                task.getTitle(),
                task.getAssignedTo(),
                task.getStatus(),
                task.getPriority()
        );
    }
}
