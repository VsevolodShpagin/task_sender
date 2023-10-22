package sender.core.service.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sender.core.request.SendPaymentRequest;
import sender.core.response.ResponseError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendPaymentValidatorImplTest {

    @Mock
    private SendPaymentRequest mockRequest;

    private final SendPaymentValidator validator = new SendPaymentValidatorImpl();

    @Test
    void validate_validInput_returnNoErrors() {
        when(mockRequest.getPayer()).thenReturn("Payer");
        when(mockRequest.getReceiver()).thenReturn("Receiver");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validate_nullPayer_returnError() {
        when(mockRequest.getPayer()).thenReturn(null);
        when(mockRequest.getReceiver()).thenReturn("Receiver");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).getMessage().contains("Payer"));
    }

    @Test
    void validate_blankPayer_returnError() {
        when(mockRequest.getPayer()).thenReturn("");
        when(mockRequest.getReceiver()).thenReturn("Receiver");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).getMessage().contains("Payer"));
    }

    @Test
    void validate_emptyReceiver_returnError() {
        when(mockRequest.getPayer()).thenReturn("Payer");
        when(mockRequest.getReceiver()).thenReturn("  ");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).getMessage().contains("Receiver"));
    }

    @Test
    void validate_invalidEMail_returnError() {
        when(mockRequest.getPayer()).thenReturn("Payer");
        when(mockRequest.getReceiver()).thenReturn("Receiver");
        when(mockRequest.getReceiverEMail()).thenReturn("receiverMail");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).getMessage().contains("E-mail"));
    }

    @Test
    void validate_invalidSum_returnError() {
        when(mockRequest.getPayer()).thenReturn("Payer");
        when(mockRequest.getReceiver()).thenReturn("Receiver");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("amount");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).getMessage().contains("Sum"));
    }

    @Test
    void validate_multipleErrors_returnErrors() {
        when(mockRequest.getPayer()).thenReturn(null);
        when(mockRequest.getReceiver()).thenReturn("  ");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        when(mockRequest.getSum()).thenReturn("99.99");
        List<ResponseError> errors = validator.validate(mockRequest);
        assertEquals(2, errors.size());
        assertTrue(errors.get(0).getMessage().contains("Payer"));
        assertTrue(errors.get(1).getMessage().contains("Receiver"));
    }

}
