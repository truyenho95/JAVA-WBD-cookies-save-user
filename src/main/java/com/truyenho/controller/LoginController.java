package com.truyenho.controller;

import com.truyenho.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

  @PostMapping("/dologin")
  public ModelAndView doLogin(@ModelAttribute("user") User user, @CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletRequest request, HttpServletResponse response) {
    ModelAndView modelAndView = new ModelAndView("login");
    if (user.getEmail().equals("admin1@gmail.com") && user.getPassword().equals("12345")) {
      if (user.getEmail() != null) {
        setUser = user.getEmail();
      }

      Cookie cookie = new Cookie("setUser", setUser);
      cookie.setMaxAge(24 * 60 * 60);
      response.addCookie(cookie);

      Cookie[] cookies = request.getCookies();
      for (Cookie ck : cookies) {
        if (ck.getName().equals("setUser")) {
          modelAndView.addObject("cookieValue", ck);
          break;
        } else {
          ck.setValue("");
          modelAndView.addObject("cookieValue", ck);
          break;
        }
      }
      modelAndView.addObject("message", "Login success. Welcome ");
    } else {
      user.setEmail("");
      Cookie cookie = new Cookie("setUser", setUser);
      modelAndView.addObject("cookieValue", cookie);
      modelAndView.addObject("message", "Login failed. Try again.");
    }
    return modelAndView;
  }

}
