package sender.web_ui.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sender.core.request.SendPaymentRequest;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String showIndexPage(
            HttpSession httpSession,
            ModelMap modelMap
    ) {
        modelMap.addAttribute("sendPaymentRequest", new SendPaymentRequest());
        //from session into attr
        //html gets placeholders from attr
        //will it fill them in another tab
        //shouldn't fill in another browser
        return "index";
    }

    @PostMapping(value = "/")
    public String processIndexPage(
            @ModelAttribute(value = "sendPaymentRequest") SendPaymentRequest request,
            ModelMap modelMap
    ) {
        //fill session from request
        return "success";
    }

}
