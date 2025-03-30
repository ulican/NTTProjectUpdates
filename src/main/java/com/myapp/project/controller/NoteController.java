package com.myapp.project.controller;

import com.myapp.project.domain.Note;
import com.myapp.project.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class NoteController {
    private final CommentService commentService;

    public NoteController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/task/{taskId}")
    public List<Note> getCommentsByTask(@PathVariable Long taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }

    @PostMapping("/task/{taskId}/owner/{ownerId}")
    public Note createComment(@RequestBody Note note,
                              @PathVariable Long taskId,
                              @PathVariable Long ownerId) {
        return commentService.createComment(note, taskId, ownerId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateComment(@PathVariable Long id,
                                              @RequestBody String content) {
        Note updatedNote = commentService.updateComment(id, content);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}