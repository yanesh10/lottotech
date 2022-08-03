package mu.yanesh.lotto.analyser.client;

import mu.yanesh.lotto.library.models.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "lotto-api-client", url = "${lotto.api.base-url}/tirage")
public interface LottoApiClient {

    @GetMapping("full")
    List<Ticket> findAll();
}
