package sender.core.service.validator;

import org.springframework.stereotype.Component;
import sender.core.request.SendPaymentRequest;
import sender.core.response.ResponseError;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SendPaymentValidatorImpl implements SendPaymentValidator {

    private static final String MAIL_FORMAT = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String REGEX_GREATER_ZERO = "0*[1-9][0-9]*(.[0-9]+)?";

    private static final String PAYER_MISSING = "PAYER_MISSING";
    private static final String RECEIVER_MISSING = "RECEIVER_MISSING";
    private static final String EMAIL_INVALID = "EMAIL_INVALID";
    private static final String SUM_INVALID = "SUM_INVALID";
    private static final String GENERAL = "GENERAL";

    private final ResourceBundle errorMessages = ResourceBundle.getBundle("errorMessage");

    @Override
    public List<ResponseError> validate(SendPaymentRequest request) {
        List<ResponseError> errors = new ArrayList<>();
        if (!isPresent(request.getPayer())) errors.add(new ResponseError(getErrorMessage(PAYER_MISSING)));
        if (!isPresent(request.getReceiver())) errors.add(new ResponseError(getErrorMessage(RECEIVER_MISSING)));
        if (!isValidMail(request.getReceiverEMail())) errors.add(new ResponseError(getErrorMessage(EMAIL_INVALID)));
        if (!isValidSum(request.getSum())) errors.add(new ResponseError(getErrorMessage(SUM_INVALID)));
        return errors;
    }

    private boolean isPresent(String input) {
        return input != null && !input.isBlank();
    }

    private boolean isValidMail(String input) {
        return isPresent(input) && input.matches(MAIL_FORMAT);
    }

    private boolean isValidSum(String input) {
        return isPresent(input) && input.matches(REGEX_GREATER_ZERO);
    }

    private String getErrorMessage(String errorCode) {
        if (errorCode == null || !errorMessages.containsKey(errorCode)) errorCode = GENERAL;
        return errorMessages.getString(errorCode);
    }

}
