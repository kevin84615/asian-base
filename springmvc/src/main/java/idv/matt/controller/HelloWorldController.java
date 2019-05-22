package idv.matt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
  
  @RequestMapping(value="/hello")
  public ModelAndView hello(){
    System.out.println("Hello World! Spring MVC!!!");
    return new ModelAndView("helloworld"); // According as ViewResolverÅCback to /WEB-INF/view/helloworld.jsp
  }
}