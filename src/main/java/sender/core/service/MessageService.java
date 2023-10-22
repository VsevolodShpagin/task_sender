package sender.core.service;

import sender.core.domain.Payment;

public interface MessageService {

    String createBody(Payment payment);

}
