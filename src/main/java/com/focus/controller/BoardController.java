package com.focus.controller;

import com.focus.po.Board;
import com.focus.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.JsonUtil.MapToJson;

@Controller
public class BoardController {

    @Resource
    BoardService boardService;

    @RequestMapping(value = "/board")
    public String fromData( Model model) {
        return "/board/list";
    }

    @RequestMapping(value = "/board/list.do")
    @ResponseBody
    public List<Board> boardList(@ModelAttribute("Board") Board BoardVO) throws Exception{

        //Map<String, Object> resultMap = new HashMap<String, Object>();
        //ModelAndView mav = new ModelAndView("jsonView");
        //mav.addObject("result" ,boardService.selectAll());
        //resultMap.put("result", boardService.selectAll());


        List<Board> boardList = boardService.selectAll();
        System.out.println(boardList);
        return boardList;
    }
}
