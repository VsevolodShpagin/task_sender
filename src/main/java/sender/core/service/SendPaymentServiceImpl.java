package sender.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sender.core.domain.Payment;
import sender.core.request.SendPaymentRequest;
import sender.core.response.ResponseError;
import sender.core.response.SendPaymentResponse;
import sender.core.service.validator.SendPaymentValidator;

import java.util.List;

@Component
public class SendPaymentServiceImpl implements SendPaymentService {

    @Autowired
    private SendPaymentValidator validator;
    @Autowired
    private MailSender mailSender;

    @Override
    public SendPaymentResponse execute(SendPaymentRequest request) {
        List<ResponseError> errors = validator.validate(request);
        if (!errors.isEmpty()) return new SendPaymentResponse(errors);
        Payment payment = new Payment(
                request.getPayer(),
                request.getReceiver(),
                request.getSum(),
                request.getDescription());
        mailSender.send(payment, request.getReceiverEMail());
        return new SendPaymentResponse();
    }

}
