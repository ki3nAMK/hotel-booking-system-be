package com.loco.demo.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.StoryForm;
import com.loco.demo.entity.Story;
import com.loco.demo.services.storyService.StoryService;

@RequestMapping("/api/v1/client")
@RestController
@CrossOrigin("*")
public class UserStoryController {
    private StoryService storyService;

    @Autowired
    public UserStoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/list/story")
    public ListResponse<Story> getListStory(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return storyService.getListStory(page - 1, limit);
    }

    @PostMapping("/story/add")
    public Story addStory(@RequestBody StoryForm storyForm) {
        return storyService.addStory(storyForm);
    }

    @GetMapping("/my-story")
    public ListResponse<Story> getMyStory(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return storyService.getMyStory(page - 1, limit);
    }
}
