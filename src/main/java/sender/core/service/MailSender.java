package sender.core.service;

import sender.core.domain.Payment;

public interface MailSender {

    void send(Payment payment, String to);

}
