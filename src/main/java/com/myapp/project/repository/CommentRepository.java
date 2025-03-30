package com.myapp.project.repository;

import com.myapp.project.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Note, Long> {
    List<Note> findByTaskId(Long taskId);
    List<Note> findByOwnerId(Long ownerId);
}