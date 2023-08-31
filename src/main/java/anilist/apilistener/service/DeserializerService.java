package anilist.apilistener.service;

import anilist.apilistener.model.Anime;
import anilist.apilistener.model.AnimeSearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface DeserializerService {

    AnimeSearchRequest deserializeAnimeSearchRequest(String json) throws JsonProcessingException;

    List<Anime> deserializeAnimeList(String json) throws JsonProcessingException;
}
