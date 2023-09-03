package anilist.apilistener.service.implementation;

import anilist.apilistener.exception.ApiErrorResponseException;
import anilist.apilistener.model.Anime;
import anilist.apilistener.model.ResponseWrapper;
import anilist.apilistener.openfeign.AnimeRequestClient;
import anilist.apilistener.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRequestClient animeRequestClient;

    @Override
    public List<Anime> requestAnimeListByParameters(Map<String, Object> animeSearchRequest) throws ApiErrorResponseException {
        ResponseWrapper response = animeRequestClient.searchAnime(animeSearchRequest);
        if (response.getData() == null) {
            log.error("Api error response status: {}, error response type: {}", response.getStatus(), response.getType());
            throw new ApiErrorResponseException("Api response contains error");
        } else {
            return animeRequestClient.searchAnime(animeSearchRequest).getData();
        }
    }
}
