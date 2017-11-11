package com.miskowskij.app.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miskowskij.app.service.AppService;

@RestController
public class AppController extends BaseController {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);

	private final static AppService appService = new AppService();

    @RequestMapping("/")
    public String index() {
		logger.debug("[index] i am root.");
		
        return appService.root();
    }
    
    @RequestMapping("/foo")
    public String foo() {
		logger.debug("[foo] i am foo.");
		
        return appService.foo();
    }
    
    @RequestMapping("/bar")
    public String bar() {
		logger.debug("[bar] i am bar.");
		
        return appService.bar();
    }
 
}