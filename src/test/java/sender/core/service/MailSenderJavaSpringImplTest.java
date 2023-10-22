package sender.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;
import sender.core.domain.Payment;
import sender.core.matcher.MessageMatcher;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailSenderJavaSpringImplTest {

    @Mock
    private JavaMailSender mockMailSender;
    @Mock
    private MessageService mockMessageService;
    @Mock
    private Payment mockPayment;

    @InjectMocks
    private MailSenderJavaSpringImpl mailSender;

    @Test
    void send_bodyCreated() {
        mailSender.send(mockPayment, "mail@mail.com");
        verify(mockMessageService).createBody(mockPayment);
    }

    @Test
    void send_sendCalled() {
        ReflectionTestUtils.setField(mailSender, "from", "from");
        ReflectionTestUtils.setField(mailSender, "subject", "subject");
        when(mockMessageService.createBody(mockPayment)).thenReturn("textBody");
        mailSender.send(mockPayment, "to");
        verify(mockMailSender).send(
                argThat(new MessageMatcher("from", "to", "subject", "textBody"))
        );
    }

}
