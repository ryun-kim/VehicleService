package com.koreait.vehicleservice.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/center")
public class CenterController {

    @Autowired BoardService service;

    @GetMapping("/customer")
    public void customer(){}

    @GetMapping("/questionboard")//클라이언트에서 보낸 url파라미터값 받를때 객체로받을땐 @RequestParam 안적는다.
    public void questionboard(Model model, BoardDto dto){
        model.addAttribute("list", service.selBoardList(dto));
    }

    @GetMapping("/guide")
    public void guide(){}

    @GetMapping("/introduction")
    public void introduction(){}

    @GetMapping("/detailquestion")
    public void detailquestion(@RequestParam int quesiboard, Model model){
        //쿼리스트링으로 파라미터를 URL로 전송할 때엔 컨트롤러에서 파라미터를 받을때 @RequestParam 을 사용한다.
        BoardVo vo = service.selBoard(quesiboard);
        BoardPrevNextVo pnVo = service.selPrevNext(vo);
        BoardCmtEntity entity = service.selCmtBoard(quesiboard);

        model.addAttribute("item", vo);
        model.addAttribute("prevnext", pnVo);
        if(entity != null) {
            model.addAttribute("cmtItem", entity);
        }
    }

    @GetMapping("/write")
    public void write(@ModelAttribute BoardEntity boardEntity, @RequestParam int quesiboard, Model model){
        if(quesiboard > 0){
           model.addAttribute("item", service.selBoard(quesiboard));
        }
    }

    @PostMapping("/write")
    public String writeProc(BoardEntity boardEntity){
        int rs = service.insBoard(boardEntity);
        System.out.println("1이면 입력성공 0이면 입력실패 : " + rs);
        return "redirect:/center/customer";
    }

    @GetMapping("/del/{quesiboard}")
    public String del(@PathVariable int quesiboard){
        service.delBoard(quesiboard);
        return "redirect:/center/customer";
    }

    @ResponseBody
    @PostMapping("/modify") //post매핑일땐 @requestbody 붙여줘야함
    public Map<String, Integer> mod(@RequestBody BoardEntity boardEntity){
        Map<String, Integer> map = new HashMap<>();
        map.put("result", service.modBoard(boardEntity));
        return map;
    }

    @ResponseBody
    @GetMapping("/board/maxpage") //get매핑일땐 @Requestbody 핑요없음
    public ResultVo selMaxPageVal(BoardDto dto){
        return service.selMaxPageVal(dto);
    }

    @ResponseBody
    @PostMapping("/getlist")
    public List<BoardEntity> getList(@RequestBody BoardDto dto){
        return service.selBoardList(dto);
    }

    @ResponseBody
    @PostMapping("/comment")
    public Map<String, Integer> comment(@RequestBody BoardCmtEntity cmtEntity){
        Map<String, Integer> map = new HashMap<>();
        map.put("result", service.insCmtBoard(cmtEntity));
        return map;
    }
}
