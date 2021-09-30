package com.kanozz.mvc.zero.mvc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/kano1")
@ResponseBody
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class KanoRequestController {

	@PostMapping("/do")
	public String doService(){

		return "kano";
	}
}
