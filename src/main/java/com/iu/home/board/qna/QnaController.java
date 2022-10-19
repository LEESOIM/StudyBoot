package com.iu.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

@RequestMapping("/qna/*")
@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<QnaVO> ar = qnaService.getList(pager);
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("add")
	public String setAdd() throws Exception {
		return "board/write";
	}
	
//	@PostMapping("add")
//	public void setAdd(QnaVO qnaVO) throws Exception {
//		int result = qnaService.setAdd(qnaVO);
//	}
	
	@PostMapping("add")				//ModelAndView와 달리 값이 파라미터에 담겨서 반환
	public String setAdd(QnaVO qnaVO, RedirectAttributes redirectAttributes, MultipartFile [] files) throws Exception {
		int result = qnaService.setAdd(qnaVO);
		redirectAttributes.addAttribute("result", result);
		return "redirect:./list";
	}
	
}
