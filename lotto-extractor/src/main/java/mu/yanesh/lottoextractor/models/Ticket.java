package mu.yanesh.lottoextractor.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private final int number1;
    private final int number2;
    private final int number3;
    private final int number4;
    private final int number5;
    private final int number6;
}
