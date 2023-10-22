package sender.core.service;

import sender.core.request.SendPaymentRequest;
import sender.core.response.SendPaymentResponse;

public interface SendPaymentService {

    SendPaymentResponse execute(SendPaymentRequest request);

}
