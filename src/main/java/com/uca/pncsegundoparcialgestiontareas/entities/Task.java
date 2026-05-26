package com.uca.pncsegundoparcialgestiontareas.entities;

import com.uca.pncsegundoparcialgestiontareas.common.priority.Priority;
import com.uca.pncsegundoparcialgestiontareas.common.status.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "task")
@Data
@Builder // Patron de diseño Builder
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos nuestros atributos
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status status;

    @Column (name = "priority")
    private Priority priority;

    @Column(name = "estimatedHours")
    private int estimatedHours;

    @Column(name = "loggedHours")
    private int loggedHours;

    @Column(name = "dueDate")
    private Date dueDate;

    @Column (name = "assignedTo")
    private String assignedTo;

    @Column (name = "active")
    private boolean active;
}