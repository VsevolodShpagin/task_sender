package sender.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import sender.core.domain.Payment;

@Component
public class MailSenderJavaSpringImpl implements MailSender {

    @Value("${spring.mail.username}")
    private String from;
    @Value("${mail.subject}")
    private String subject;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageService messageService;

    @Override
    public void send(Payment payment, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageService.createBody(payment));
        mailSender.send(message);
    }

}
