package sender.core.matcher;

import org.mockito.ArgumentMatcher;
import org.springframework.mail.SimpleMailMessage;

import java.util.Objects;

public class MessageMatcher implements ArgumentMatcher<SimpleMailMessage> {

    private final String from;
    private final String to;
    private final String subject;
    private final String textBody;

    public MessageMatcher(String from, String to, String subject, String textBody) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.textBody = textBody;
    }

    @Override
    public boolean matches(SimpleMailMessage message) {
        return from.equals(message.getFrom()) &&
                to.equals(Objects.requireNonNull(message.getTo())[0]) &&
                subject.equals(message.getSubject()) &&
                textBody.equals(message.getText());

    }

}
