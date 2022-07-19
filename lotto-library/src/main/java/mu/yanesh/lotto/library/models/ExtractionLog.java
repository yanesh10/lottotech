package mu.yanesh.lotto.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Document(collection = "batch_log")
public class ExtractionLog {
    @Id
    private final String id;
    private final ZonedDateTime dateTime;
}
