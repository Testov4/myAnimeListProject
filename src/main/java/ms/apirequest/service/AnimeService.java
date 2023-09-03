package ms.apirequest.service;

import ms.apirequest.exception.ApiErrorResponseException;
import ms.apirequest.model.Anime;
import java.util.List;
import java.util.Map;

public interface AnimeService {

    List<Anime> requestAnimeListByParameters(Map<String, Object> animeSearchRequest) throws ApiErrorResponseException;
}