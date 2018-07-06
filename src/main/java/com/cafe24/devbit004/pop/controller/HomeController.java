package com.cafe24.devbit004.pop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Petri Kainulainen*/


@Controller
public class HomeController {
	@Autowired
    private ServletContext servletContext;
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
    

	@RequestMapping( "/popup")
	@ResponseBody
	@CrossOrigin
	public String popup() {
		/*FileInputStream input = null;
        try{
            // 화면에 표시하고자 하는 파일을 선택한다.
            File file = new File("c:\\example\\File\\umejintan_new.txt");
             
            // FileInputStream 는 File object를 생성자 인수로 받을 수 있다.         
            input = new FileInputStream(file);
            int i = 0;
            String html = "";
            while((i = input.read()) != -1) {
                html += i;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try{
                // 생성된 InputStream Object를 닫아준다.
                input.close();
            } catch(IOException io) {}
        }*/
		String response="";
		String temp="";
        InputStream inputStream = null;
        try {
            inputStream = servletContext.getResourceAsStream("/WEB-INF/popup_main.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while(true) {
            	temp=bufferedReader.readLine();
            	if(temp == null) {
            		break;
            	}
            	response+=temp;
            }
            
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (inputStream != null) {
               try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        }
        
		return response;
	}
}
