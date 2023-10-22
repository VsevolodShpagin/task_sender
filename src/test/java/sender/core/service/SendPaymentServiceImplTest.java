package sender.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sender.core.matcher.PaymentMatcher;
import sender.core.request.SendPaymentRequest;
import sender.core.response.ResponseError;
import sender.core.response.SendPaymentResponse;
import sender.core.service.validator.SendPaymentValidator;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendPaymentServiceImplTest {

    @Mock
    private SendPaymentValidator mockValidator;
    @Mock
    private MailSender mockMailSender;
    @Mock
    private SendPaymentRequest mockRequest;
    @Mock
    private ResponseError mockResponseError;

    @InjectMocks
    private SendPaymentServiceImpl service;

    @Test
    void execute_invalidRequest_returnErrors() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockResponseError, mockResponseError));
        SendPaymentResponse response = service.execute(mockRequest);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void execute_invalidRequest_notCallMailSender() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockResponseError, mockResponseError));
        service.execute(mockRequest);
        verify(mockMailSender, times(0)).send(any(), anyString());
    }

    @Test
    void execute_validInput_callMailSender() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getPayer()).thenReturn("payer");
        when(mockRequest.getReceiver()).thenReturn("receiver");
        when(mockRequest.getSum()).thenReturn("99.99");
        when(mockRequest.getDescription()).thenReturn("description");
        when(mockRequest.getReceiverEMail()).thenReturn("receiver@mail.com");
        service.execute(mockRequest);
        verify(mockMailSender).send(
                argThat(new PaymentMatcher("payer", "receiver", "99.99", "description")),
                eq("receiver@mail.com"));
    }

    @Test
    void execute_validInput_returnNoErrors() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        SendPaymentResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

}
