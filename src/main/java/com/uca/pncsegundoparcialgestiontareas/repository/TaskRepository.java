package com.uca.pncsegundoparcialgestiontareas.repository;

import com.uca.pncsegundoparcialgestiontareas.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);
}
