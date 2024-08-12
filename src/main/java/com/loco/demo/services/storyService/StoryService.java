package com.loco.demo.services.storyService;

import com.loco.demo.DTO.JSON.ListResponse;
import com.loco.demo.DTO.JSON.StoryForm;
import com.loco.demo.entity.Story;

public interface StoryService {
    public ListResponse<Story> getListStory(int page,int limit);

    public Story addStory(StoryForm storyForm);
}
