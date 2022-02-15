package com.koreait.vehicleservice.center;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/center")
public class CenterController {

    @GetMapping("/customer")
    public void customer(){}

    @GetMapping("/questionboard")
    public void questionboard(){}

    @GetMapping("/guide")
    public void guide(){}

    @GetMapping("/introduction")
    public void introduction(){}

    @GetMapping("/notice")
    public void notice(){}

    @GetMapping("/detailquestion")
    public void detailquestion(){}

    @GetMapping("/detailnotice")
    public void detailnotice(){}

    @GetMapping("/write")
    public void write(){}
}
