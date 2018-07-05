package org.springframework.social.cafe24.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.cafe24.connect.Cafe24OAuth2Template;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class UserInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    private final UsersConnectionRepository repository;

    private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

    private static String CONTEXT_PATH;

    public UserInterceptor(UsersConnectionRepository connectionRepository) {
        this.repository = connectionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CONTEXT_PATH = request.getContextPath();
        logger.info("UserInterceptor preHandle called ");
//        return super.preHandle(request, response, handler);
        String currUrl = CONTEXT_PATH + request.getServletPath();
        logger.info("preHandle: " + currUrl);
        if (currUrl.contains("/login")){
            logger.info("preHandle in " + CONTEXT_PATH + "/login");
            return true;

        }
        if (currUrl.contains("/connect2/cafe24")){
            logger.info("preHandle in " + CONTEXT_PATH + "/connect2/cafe24");
            return true;

        }
        if (currUrl.contains("/connect2/result")){
            logger.info("preHandle in " + CONTEXT_PATH + "/connect2/result");
            return true;

        }
        if (currUrl.contains("/connect2/scripttags")){
            logger.info("preHandle in " + CONTEXT_PATH + "/connect2/scripttags");
            return true;

        }
        if (currUrl.contains("/connect2/allScripttags")){
            logger.info("preHandle in " + CONTEXT_PATH + "/connect2/allScripttags");
            return true;
        }
        if (currUrl.contains("/connect2/delete/scripttag")){
            logger.info("preHandle in " + CONTEXT_PATH + "/connect2/delete/scripttag");
            return true;
        }


        String userId = userCookieGenerator.readCookieValue(request);
        logger.info("preHandle userId: " + userId);
        if (userId != null) {
            logger.info("preHandle userId != null");

            /*  */
            if (!repository
                    .findUserIdsConnectedTo("cafe24", Collections.singleton(userId))
                    .isEmpty()) {
                logger.info("preHandle userId Collections.singleton(userId) is Empty");

                logger.info("loading user {} from cookie", userId);
                SecurityContext.setCurrentUser(new User(userId));
                return true;
            } else {
                logger.warn("user {} is not known!", userId);
                logger.info("preHandle userId Collections.singleton(userId) is Not Empty");

                userCookieGenerator.removeCookie(response);
            }
        } else {
            /* 실제 접속한 유저를 등록하는 과정 */
            userId = Cafe24OAuth2Template.getMallId();
            SecurityContext.setCurrentUser(new User(userId));
            logger.info("preHandle userId == null");
//            response.sendRedirect(CONTEXT_PATH + "/connect2/cafe24");
            response.sendRedirect(CONTEXT_PATH + "/login");
            return false;

        }
        response.sendRedirect(CONTEXT_PATH + "/connect2/result");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
