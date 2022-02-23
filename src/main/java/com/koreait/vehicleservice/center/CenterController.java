package com.koreait.vehicleservice.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/center")
public class CenterController {

    @Autowired BoardService service;

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
    public void write(@ModelAttribute BoardEntity boardEntity){}

    @PostMapping("/write")
    public void writeProc(BoardEntity boardEntity){
        int rs = service.insBoard(boardEntity);
        System.out.println("1이면 입력성공 0이면 입력실패 : " + rs);
    }
}
