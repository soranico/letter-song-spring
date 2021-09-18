package com.kanozz.mvc.zero.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kano")
@ResponseBody
public class KanoController {
	private static final Logger log = LoggerFactory.getLogger(KanoController.class);
	public KanoController(){
		log.info("controller create");
	}
	@PostMapping("/do")
	public String  doService(){

		return "kano";
	}

}
