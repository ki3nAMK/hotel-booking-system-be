package com.loco.demo.services.storyService;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.StoryForm;
import com.loco.demo.entity.Story;
import com.loco.demo.repository.Story.StoryRepo;
import com.loco.demo.services.userService.UserService;

@Service
public class StoryServiceImpl implements StoryService {
    private StoryRepo storyRepo;
    private UserService userService;

    @Autowired
    public StoryServiceImpl(StoryRepo storyRepo, UserService userService) {
        this.storyRepo = storyRepo;
        this.userService = userService;
    }

    @Override
    public ListResponse<Story> getListStory(int page, int limit) {
        Date now = new Date();
        Date oneWeekAgo = new Date(now.toInstant().plus(-7, ChronoUnit.DAYS).toEpochMilli());

        Pageable pageable = PageRequest.of(page, limit);
        Page<Story> pageStory = storyRepo.findByShareDayBetweenOrderByShareDayDesc(pageable, oneWeekAgo, now);

        return new ListResponse<Story>(pageStory.getContent(), pageStory.getTotalElements());
    }

    @Override
    public Story addStory(StoryForm storyForm) {
        User myUser = userService.getMyInfo();
        Story story = new Story();
        story.setId(UUID.randomUUID().toString());
        story.setShareDay(new Date());
        story.setUser(myUser);
        story.setImg(storyForm.getImg());

        return storyRepo.save(story);
    }

    @Override
    public ListResponse<Story> getMyStory(int page, int limit) {
        User myUser = userService.getMyInfo();
        Pageable pageable=PageRequest.of(page, limit);
        Page<Story> pageStory = storyRepo.findByUser(myUser,pageable);
        return new ListResponse<Story>(pageStory.getContent(), pageStory.getTotalElements());
    }

    @Override
    public void deleteStory(String id) {
        Story story = storyRepo.findById(id).orElseThrow(()->new RuntimeException("NOT FOUND STORY IN DATABASE"));
        User myUser = userService.getMyInfo();
        if(myUser.getUserId().equals(story.getUser().getUserId())){
            storyRepo.deleteById(id);
        }
        else throw new RuntimeException("Not your own story");
    }
}