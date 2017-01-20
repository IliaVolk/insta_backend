package inproject.controller;

import inproject.entity.InstagramAuthUser;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    InstagramAuthUser getUser(HttpServletRequest request){
        return (InstagramAuthUser) request.getAttribute("user");
    }
}
