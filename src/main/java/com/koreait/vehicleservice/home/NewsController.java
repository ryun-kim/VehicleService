package com.koreait.vehicleservice.home;

import com.koreait.vehicleservice.home.model.NewsEntity;
import com.koreait.vehicleservice.home.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @Autowired private NewsService service;

    @GetMapping("/news")
    public void home(NewsEntity entity, Model model) throws Exception {
        model.addAttribute("newsList", service.getData(entity));
    }
}
