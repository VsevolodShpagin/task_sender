package sender.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPaymentRequest {

    private String payer;
    private String receiver;
    private String receiverEMail;
    private String sum;
    private String description;

}
