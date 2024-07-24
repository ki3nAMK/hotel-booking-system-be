package com.loco.demo.Controller.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Comment;
import com.loco.demo.services.commentService.CommentService;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin("*")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list/{id}")
    public ListResponse<Comment> getListCommentByHotel(@PathVariable String id,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        return commentService.getListCommentByHotel(id, page-1, limit);
    }
}
