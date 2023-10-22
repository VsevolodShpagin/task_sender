package sender.web_ui.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sender.core.request.SendPaymentRequest;
import sender.core.response.SendPaymentResponse;
import sender.core.service.SendPaymentService;

@Controller
public class IndexController {

    @Autowired
    private SendPaymentService sendPaymentService;

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
        SendPaymentResponse response = sendPaymentService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("sendPaymentErrors", response.getErrors());
            return "index";
        }
        //fill session from request
        return "success";
    }

}
