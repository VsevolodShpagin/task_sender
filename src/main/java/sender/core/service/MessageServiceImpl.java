package sender.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sender.core.domain.Payment;

@Component
public class MessageServiceImpl implements MessageService {

    @Value("${mail.text}")
    private String text;
    @Value("${mail.description}")
    private String description;

    @Override
    public String createBody(Payment payment) {
        String mainText = String.format(text, payment.getPayer(), payment.getReceiver(), payment.getSum());
        String descriptionText = (payment.getDescription() != null && !payment.getDescription().isBlank())
                ? "\n\r" + String.format(description, payment.getDescription())
                : "";
        return mainText + descriptionText;
    }

}
