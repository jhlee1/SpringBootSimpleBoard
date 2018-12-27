package lee.joohan.SpringBootSimpleBoard.controller;

import lee.joohan.SpringBootSimpleBoard.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping({"/", ""})
    public String boards(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));

        return  "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));

        return "/board/list";
    }
}
