package anilist.apilistener.service;

import java.util.Map;

public interface DeserializerService {

    Map<String, Object> deserializeAnimeSearchRequest(String json);
}
