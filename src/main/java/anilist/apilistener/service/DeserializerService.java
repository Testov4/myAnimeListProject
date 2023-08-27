package anilist.apilistener.service;

import anilist.apilistener.model.AnimeSearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeserializerService {

    AnimeSearchRequest convertToAnimeSearchRequest(String json) throws JsonProcessingException;
}
