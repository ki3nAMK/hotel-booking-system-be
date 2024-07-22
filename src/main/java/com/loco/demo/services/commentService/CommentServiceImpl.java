package com.loco.demo.services.commentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Comment;
import com.loco.demo.repository.CommentRepo.CommentRepo;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public ListResponse<Comment> getListCommentByHotel(String id, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Comment> pageComment = commentRepo.getListCommentByHotel(id, pageable);
        return new ListResponse<Comment>(pageComment.getContent(), pageComment.getTotalElements());
    }

}
