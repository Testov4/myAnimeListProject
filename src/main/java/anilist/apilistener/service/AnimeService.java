package anilist.apilistener.service;

import anilist.apilistener.model.Anime;
import anilist.apilistener.model.AnimeSearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AnimeService {

    Anime findAnimeById(Integer id) throws JsonProcessingException;

    List<Anime> findAnimeByParameters(AnimeSearchRequest animeSearchRequest) throws JsonProcessingException;
}
