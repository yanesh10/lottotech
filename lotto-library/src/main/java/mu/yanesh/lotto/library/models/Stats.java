package mu.yanesh.lotto.library.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Stats {
    @Id
    private String id;
    private Date date;
    private List<Integer> numbers;
    private int frequency;
    private int combination;
}
