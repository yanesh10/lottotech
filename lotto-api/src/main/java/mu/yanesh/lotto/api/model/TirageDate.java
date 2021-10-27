package mu.yanesh.lotto.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class TirageDate {

    @NotNull
    @Pattern(regexp = "/^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-d{4}$/")
    private LocalDate tirageDate;

}
