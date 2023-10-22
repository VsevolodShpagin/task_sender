package sender.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sender.core.request.SendPaymentRequest;
import sender.core.response.SendPaymentResponse;
import sender.core.service.SendPaymentService;

@RestController
@RequestMapping("/")
public class IndexRestController {

    @Autowired
    private SendPaymentService sendPaymentService;

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public SendPaymentResponse send(@RequestBody SendPaymentRequest request) {
        return sendPaymentService.execute(request);
    }

}
