package com.loco.demo.repository.Story;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Story;

@Repository
public interface StoryRepo extends JpaRepository<Story, String> {
    public Page<Story> findByShareDayBetweenOrderByShareDayDesc(Pageable pageable, Date oneWeekAgo, Date now);

    public Optional<Story> findByUser(User user);

    public Page<Story> findByUser(User user, Pageable pageable);
}
