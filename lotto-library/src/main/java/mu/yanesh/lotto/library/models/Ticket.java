package mu.yanesh.lotto.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document(collection = "raw_tirage")
public class Ticket {
    @Id
    private final String id;
    private final int number1;
    private final int number2;
    private final int number3;
    private final int number4;
    private final int number5;
    private final int number6;
    private final LocalDate tirageDate;
}
