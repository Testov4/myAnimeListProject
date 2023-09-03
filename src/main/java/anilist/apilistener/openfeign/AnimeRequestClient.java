package anilist.apilistener.openfeign;

import anilist.apilistener.model.ResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(value = "anime-request", url = "${api.url}")
@Component
public interface AnimeRequestClient {

    @GetMapping
    ResponseWrapper searchAnime(@RequestParam Map<String, Object> params);
}
