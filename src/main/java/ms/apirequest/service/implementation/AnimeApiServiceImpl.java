package ms.apirequest.service.implementation;

import ms.apirequest.exception.ApiErrorResponseException;
import ms.apirequest.model.Anime;
import ms.apirequest.model.ResponseWrapper;
import ms.apirequest.openfeign.AnimeRequestClient;
import ms.apirequest.service.AnimeApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimeApiServiceImpl implements AnimeApiService {

    private final AnimeRequestClient animeRequestClient;

    @Override
    public List<Anime> requestAnimeListByParameters(Map<String, Object> animeSearchRequest) throws ApiErrorResponseException {
        ResponseWrapper response = animeRequestClient.searchAnime(animeSearchRequest);
        if (response.getData() == null) {
            log.error("Api error response status: {}, error response type: {}", response.getStatus(), response.getType());
            throw new ApiErrorResponseException("Api response contains error");
        } else {
            return response.getData();
        }
    }
}
