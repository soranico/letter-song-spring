package com.kanozz.mvc.zero.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/kano")
@ResponseBody
@SuppressWarnings("all")
public class KanoController {
	private static final Logger log = LoggerFactory.getLogger(KanoController.class);

	/**
	 *
	 * @see org.springframework.context.support.AbstractApplicationContext#postProcessBeanFactory
	 *
	 * 其子类设置了依赖替换
	 * @see org.springframework.web.context.support.AbstractRefreshableWebApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory)
	 *
	 *
	 * 注入的是这个
	 * @see org.springframework.web.context.support.WebApplicationContextUtils.RequestObjectFactory
	 */
	@Autowired
	private HttpServletRequest httpRequest;

	public KanoController(){
		log.info("controller create");
	}
	@PostMapping("/do")
	public String doService(HttpServletRequest httpRequest){

		int kano = 1/0;



		return "kano";
	}


	@PostMapping("/do1")
	public String doService1(@RequestBody Map<String,Object> map, HttpServletRequest httpRequest){

		return "kano1";
	}



	@PostMapping("/do2")
	public String doService2(){
		log.info("httpRequest = {}",httpRequest);
		return "kano1";
	}

	@ExceptionHandler(Exception.class)
	public void exceptionHandle(){
		log.error("exception");
	}

}
