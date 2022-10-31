package com.iu.home.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

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
	public String setAdd(@ModelAttribute QnaVO qnaVO) throws Exception {
		return "board/write";
	}
		
	@PostMapping("add")																		//ModelAndView와 달리 값이 파라미터에 담겨서 반환
	public ModelAndView setAdd(ModelAndView mv, @Valid QnaVO qnaVO, BindingResult bindingResult, RedirectAttributes redirectAttributes, MultipartFile [] files) throws Exception {
		if(bindingResult.hasErrors()) {
			mv.setViewName("board/write");
			return mv;
		}
//		int result = qnaService.setAdd(qnaVO);
		mv.setViewName("redirect:./list");
//		redirectAttributes.addAttribute("result", result);
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("qnaVO", qnaVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("qnaVO", qnaVO);
		mv.setViewName("board/update");
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(QnaVO qnaVO, MultipartFile [] files) throws Exception {
		int result = qnaService.setUpdate(qnaVO);
		return "redirect:detail?num="+qnaVO.getNum();
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int setFileDelete(QnaFileVO qnaFileVO) throws Exception {
		return qnaService.setFileDelete(qnaFileVO);
	}
	
}
