package com.uca.pncsegundoparcialgestiontareas.dto.request;

import com.uca.pncsegundoparcialgestiontareas.common.priority.Priority;
import com.uca.pncsegundoparcialgestiontareas.common.status.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Builder
public record TaskDTORequest(
        @UniqueElements(message = "Task name needs to be unique")
        @NotNull(message = "Task must not be null")
        String title,

        String description,

        Status status,

        Priority priority,

        @Min(value = 1, message = "The estimated hours must be over or equal to 1")
        int estimatedHours,


        @Min(value = 0, message = "The logged items must be over or equal to 1")
        int loggedHours,

        Date dueDate,

        @NotNull(message = "Responsible name must not be null")

        String assignedTo,

        boolean active
)
{
}
