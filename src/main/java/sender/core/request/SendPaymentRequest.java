package sender.core.request;

import lombok.Data;

@Data
public class SendPaymentRequest {

    private String payer;
    private String receiver;
    private String receiverEMail;
    private String sum;
    private String description;

}
