package com.loco.demo.Controller.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.CommentForm;
import com.loco.demo.entity.Comment;
import com.loco.demo.services.commentService.CommentService;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/hotel")
@RestController
@CrossOrigin("*")
public class HotelCommentController {
    private CommentService commentService;

    @Autowired
    public HotelCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}/comments/upload")
    public Comment addComment(@PathVariable String id, @RequestBody @Valid CommentForm commentForm) {
        return commentService.addComment(id, commentForm);
    }
}
