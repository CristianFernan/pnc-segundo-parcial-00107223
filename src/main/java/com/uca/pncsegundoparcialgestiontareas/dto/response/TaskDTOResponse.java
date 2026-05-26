package com.uca.pncsegundoparcialgestiontareas.dto.response;

import com.uca.pncsegundoparcialgestiontareas.common.priority.Priority;
import com.uca.pncsegundoparcialgestiontareas.common.status.Status;

public record TaskDTOResponse(
        String name,
        String assignedTo,
        Status status,
        Priority priority) {
}
