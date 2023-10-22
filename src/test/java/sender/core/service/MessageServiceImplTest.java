package sender.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import sender.core.domain.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private Payment mockPayment;

    private final MessageService messageService = new MessageServiceImpl();

    @Test
    void createBody_descriptionProvided_textCreatedWithDescription() {
        ReflectionTestUtils.setField(messageService, "text", "Payment from %s to %s of %s sent.");
        ReflectionTestUtils.setField(messageService, "description", "Description: %s.");
        when(mockPayment.getPayer()).thenReturn("payer");
        when(mockPayment.getReceiver()).thenReturn("receiver");
        when(mockPayment.getSum()).thenReturn("99.99");
        when(mockPayment.getDescription()).thenReturn("description");
        String result = messageService.createBody(mockPayment);
        assertEquals("Payment from payer to receiver of 99.99 sent.\n\rDescription: description.", result);
    }

    @Test
    void createBody_noDescription_textCreatedWithoutDescription() {
        ReflectionTestUtils.setField(messageService, "text", "Payment from %s to %s of %s sent.");
        ReflectionTestUtils.setField(messageService, "description", "Description: %s.");
        when(mockPayment.getPayer()).thenReturn("payer");
        when(mockPayment.getReceiver()).thenReturn("receiver");
        when(mockPayment.getSum()).thenReturn("99.99");
        when(mockPayment.getDescription()).thenReturn("");
        String result = messageService.createBody(mockPayment);
        assertEquals("Payment from payer to receiver of 99.99 sent.", result);
    }

}
