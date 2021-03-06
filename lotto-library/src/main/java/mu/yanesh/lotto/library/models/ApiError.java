package mu.yanesh.lotto.library.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private LocalDateTime dateTime;
    private String errorMessage;
}
