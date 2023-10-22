package sender.core.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SendPaymentResponse extends Response {

    public SendPaymentResponse(List<ResponseError> errors) {
        super(errors);
    }

}
