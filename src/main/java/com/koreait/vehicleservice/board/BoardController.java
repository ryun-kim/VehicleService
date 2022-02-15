package com.koreait.vehicleservice.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {
    @GetMapping("/detail")
    public void detail(){

    }

    @GetMapping("/selPage")
    public void selPage(){

    }
}
