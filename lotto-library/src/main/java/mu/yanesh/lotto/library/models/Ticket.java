package mu.yanesh.lotto.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Ticket {
    private final int number1;
    private final int number2;
    private final int number3;
    private final int number4;
    private final int number5;
    private final int number6;
    private final LocalDate tirageDate;
}
