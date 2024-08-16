package com.loco.demo.services.commentService;

import com.loco.demo.DTO.JSON.CommentForm;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Comment;

public interface CommentService {
    public ListResponse<Comment> getListCommentByHotel(String id, int page, int limit);

    public Comment addComment(String id, CommentForm commentForm);
}
