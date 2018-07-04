package com.cafe24.devbit004.pop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Petri Kainulainen*/


@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    protected static final String VIEW_NAME_HOMEPAGE = "index";

    protected static final String VIEW_NAME_LOGIN_PAGE = "user/login";

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage() {
        logger.debug("Rendering homepage.");
        return VIEW_NAME_HOMEPAGE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(HttpServletRequest request) {
        logger.debug("Rendering login page.");


        return VIEW_NAME_LOGIN_PAGE;
    }
}
