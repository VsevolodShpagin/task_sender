package sender.web_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String showIndexPage() {
        return "index";
    }

    @PostMapping(value = "/")
    public String processIndexPage() {
        return "success";
    }

}
