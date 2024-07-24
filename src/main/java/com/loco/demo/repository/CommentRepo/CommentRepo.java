package com.loco.demo.repository.CommentRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, String> {
    @Query("SELECT c FROM Comment c WHERE c.hotel.id=:hotel")
    public Page<Comment> getListCommentByHotel(@Param("hotel") String hotel, Pageable pageable);
}
