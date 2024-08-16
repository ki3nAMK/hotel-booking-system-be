package com.loco.demo.services.commentService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.CommentForm;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.entity.Comment;
import com.loco.demo.entity.Hotel;
import com.loco.demo.entity.HotelRating;
import com.loco.demo.entity.Reservation;
import com.loco.demo.repository.AuthenRepo.RoleAuthenRepo;
import com.loco.demo.repository.CommentRepo.CommentRepo;
import com.loco.demo.services.hotelService.HotelService;
import com.loco.demo.services.reservationService.ReservationService;
import com.loco.demo.services.userService.UserService;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepo;
    private UserService userService;
    private ReservationService reservationService;
    private HotelService hotelService;
    private RoleAuthenRepo roleAuthenRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo, UserService userService, ReservationService reservationService,
            HotelService hotelService, RoleAuthenRepo roleAuthenRepo) {
        this.commentRepo = commentRepo;
        this.userService = userService;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
        this.roleAuthenRepo = roleAuthenRepo;
    }

    @Override
    public ListResponse<Comment> getListCommentByHotel(String id, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Comment> pageComment = commentRepo.getListCommentByHotel(id, pageable);
        return new ListResponse<Comment>(pageComment.getContent(), pageComment.getTotalElements());
    }

    @Override
    public Comment addComment(String id, CommentForm commentForm) {
        User myUser = userService.getMyInfo();
        Role roleSeller = roleAuthenRepo.findByAuthority("SELLER").get();
        Hotel hotel = hotelService.getHotelById(id);
        Boolean check = false;
        if (myUser.getAuthorities().contains(roleSeller)
                && myUser.getUserId().equals(hotel.getSeller().getUserId())) {
            check = true;
        }
        if (check == false) {
            List<Reservation> reservations = reservationService.findByHotelIdAndUser(id, myUser);
            for (Reservation reservation : reservations) {
                if (reservation.getStatus() == 1 || reservation.getStatus() == 2) {
                    check = true;
                    break;
                }
            }
        }
        if (check) {
            Comment comment = new Comment();
            comment.setId(UUID.randomUUID().toString());
            comment.setUser(myUser);
            comment.setCreateAt(new Date());
            comment.setContent(commentForm.getContent());
            HotelRating rating = commentForm.getRating();
            rating.setId(UUID.randomUUID().toString());
            comment.setRating(rating);
            comment.setHotel(hotel);
            commentRepo.save(comment);
            return comment;
        } else
            throw new AccessDeniedException("You can't add comment in hotel profile not having status checked");
    }
}
