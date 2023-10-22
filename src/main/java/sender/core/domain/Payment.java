package sender.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {

    private final String payer;
    private final String receiver;
    private final String sum;
    private final String description;

}
