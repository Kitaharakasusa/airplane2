package hyg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testcontroller {
    @RequestMapping("/hello")
    public String hello()
    {
        return "Search";
    }
}
