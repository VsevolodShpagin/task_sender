package sender.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private List<ResponseError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
