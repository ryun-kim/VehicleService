package com.koreait.vehicleservice.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/center")
public class CenterController {

    @Autowired BoardService service;

    @GetMapping("/customer")
    public void customer(){}

    @GetMapping("/questionboard")
    public void questionboard(Model model){
        model.addAttribute("list", service.selBoardList());
    }

    @GetMapping("/guide")
    public void guide(){}

    @GetMapping("/introduction")
    public void introduction(){}

    @GetMapping("/notice")
    public void notice(){}

    @GetMapping("/detailquestion")
    public void detailquestion(@RequestParam int quesiboard, Model model){
        //쿼리스트링으로 파라미터를 URL로 전송할 때엔 컨트롤러에서 파라미터를 받을때 @RequestParam 을 사용한다.
        model.addAttribute("item", service.selBoard(quesiboard));
    }

    @GetMapping("/detailnotice")
    public void detailnotice(){}

    @GetMapping("/write")
    public void write(@ModelAttribute BoardEntity boardEntity){}

    @PostMapping("/write")
    public String writeProc(BoardEntity boardEntity){
        int rs = service.insBoard(boardEntity);
        System.out.println("1이면 입력성공 0이면 입력실패 : " + rs);
        return "redirect:/center/customer";
    }
}
