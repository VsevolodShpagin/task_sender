package sender.core.service.validator;

import sender.core.request.SendPaymentRequest;
import sender.core.response.ResponseError;

import java.util.List;

public interface SendPaymentValidator {

    List<ResponseError> validate(SendPaymentRequest request);

}
