package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.MessageRepository;

import jakarta.annotation.PostConstruct;

@Controller
//メインのコントロールクラス　ルーティングを行う
public class MessageController {
	
	@Autowired
	MessageRepository repository;
	
	//初期設定のダミーのデータ
	@PostConstruct
	public void init() {
		Message m1 = new Message();
		m1.setMessageBody("法律の勉強が好きです。");
		m1.setName("若槻礼次郎");
		m1.setPostedDateTime(MessageBoardUtilities.currentDateTime());
		repository.saveAndFlush(m1);
		
		
		Message m2 = new Message();
		m2.setMessageBody("アメリカ旅行が好きです。");
		m2.setName("高橋是清");
		m2.setPostedDateTime(MessageBoardUtilities.currentDateTime());
		repository.saveAndFlush(m2);

		Message m3 = new Message();
		m3.setMessageBody("通貨の歴史に興味があります。");
		m3.setName("荻原重秀");
		m3.setPostedDateTime(MessageBoardUtilities.currentDateTime());
		repository.saveAndFlush(m3);

	}
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView  mav) {
		
		mav.setViewName("index");
		
		return mav ;
	}
	
	@GetMapping("/message")
	public ModelAndView MessageGetHandler(@ModelAttribute("formData") Message message,ModelAndView mav) {
		mav.setViewName("message");
		mav.addObject("title","伝言の一覧");
		Iterable<Message> list = repository.findAllByOrderByIdDesc();
		mav.addObject("data",list);
		String ctime = MessageBoardUtilities.currentDateTime();
		mav.addObject("ctime",ctime);
		return mav;
	}
	
	//投稿結果画面
	@PostMapping("/message")
	public ModelAndView MessagePostHandler(@ModelAttribute("formData") @Validated Message message,BindingResult result,ModelAndView mav) {
		ModelAndView nextPage = null;
		if(!result.hasErrors()) {
			message.setPostedDateTime(MessageBoardUtilities.currentDateTime());
			repository.saveAndFlush(message);
			nextPage = new ModelAndView("redirect:/message");
		} else {
			mav.setViewName("message");
			mav.addObject("title","チャットボード");
			Iterable<Message> list = repository.findAllByOrderByIdDesc();
			mav.addObject("data",list);
			String ctime = MessageBoardUtilities.currentDateTime();
			mav.addObject("ctime",ctime);
			nextPage = mav;
		}
		return nextPage ;
	}	
}

