package sender.core.matcher;

import org.mockito.ArgumentMatcher;
import sender.core.domain.Payment;

public class PaymentMatcher implements ArgumentMatcher<Payment> {

    private final String payer;
    private final String receiver;
    private final String sum;
    private final String description;

    public PaymentMatcher(String payer, String receiver, String sum, String description) {
        this.payer = payer;
        this.receiver = receiver;
        this.sum = sum;
        this.description = description;
    }

    @Override
    public boolean matches(Payment payment) {
        return payer.equals(payment.getPayer()) &&
                receiver.equals(payment.getReceiver()) &&
                sum.equals(payment.getSum()) &&
                description.equals(payment.getDescription());
    }

}
