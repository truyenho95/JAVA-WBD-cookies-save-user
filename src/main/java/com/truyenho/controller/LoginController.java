package com.truyenho.controller;

import com.truyenho.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

@Controller
@SessionAttributes("user")
public class LoginController {

  @ModelAttribute("user")
  public User setUpUserForm() {
    return new User();
  }

  @RequestMapping("/login")
  public ModelAndView index(@CookieValue(value = "setUser", defaultValue = "") String setUser) {
    Cookie cookie = new Cookie("setUser", setUser);
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("cookieValue", cookie);
    return modelAndView;
  }

}
