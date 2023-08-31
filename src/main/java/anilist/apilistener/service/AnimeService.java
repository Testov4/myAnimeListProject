package anilist.apilistener.service;

import anilist.apilistener.model.AnimeSearchRequest;

public interface AnimeService {

    String requestAnimeListByParameters(AnimeSearchRequest animeSearchRequest);
}
