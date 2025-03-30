package com.myapp.project.service;

import com.myapp.project.domain.Note;
import com.myapp.project.domain.Task;
import com.myapp.project.domain.Owner;
import com.myapp.project.repository.CommentRepository;
import com.myapp.project.repository.TaskRepository;
import com.myapp.project.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final OwnerRepository ownerRepository;

    public CommentService(CommentRepository commentRepository,
                          TaskRepository taskRepository,
                          OwnerRepository ownerRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.ownerRepository = ownerRepository;
    }

    public List<Note> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    public Note createComment(Note note, Long taskId, Long ownerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        note.setTask(task);
        note.setOwner(owner);
        return commentRepository.save(note);
    }

    public Note updateComment(Long id, String newContent) {
        Note note = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
        note.setContent(newContent);
        return commentRepository.save(note);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("Note not found");
        }
        commentRepository.deleteById(id);
    }
}