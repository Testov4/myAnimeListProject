package anilist.apilistener.service;

import anilist.apilistener.exception.ApiErrorResponseException;
import anilist.apilistener.model.Anime;
import java.util.List;
import java.util.Map;

public interface AnimeService {

    List<Anime> requestAnimeListByParameters(Map<String, Object> animeSearchRequest) throws ApiErrorResponseException;
}
