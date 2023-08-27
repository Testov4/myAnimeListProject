package anilist.apilistener.service.implementation;

import anilist.apilistener.model.Anime;
import anilist.apilistener.model.AnimeListWrapper;
import anilist.apilistener.model.AnimeSearchRequest;
import anilist.apilistener.model.AnimeWrapper;
import anilist.apilistener.service.AnimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimeServiceImpl implements AnimeService {

    private final String url = "https://api.jikan.moe/v4/anime";

    private final RestTemplate restTemplate;

    final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Anime findAnimeById(Integer id) throws JsonProcessingException {
        return jsonUrlToAnimeObject("/" + id);
    }

    @Override
    public List<Anime> findAnimeByParameters(AnimeSearchRequest animeSearchRequest) throws JsonProcessingException {
        String json = restTemplate.getForObject(buildSearchUrl(animeSearchRequest), String.class);
        return mapper.readValue(json, AnimeListWrapper.class).getData();
    }

    private Anime jsonUrlToAnimeObject(String jsonUrl) throws JsonProcessingException {
        String json = restTemplate.getForObject(url + jsonUrl, String.class);
        return mapper.readValue(json, AnimeWrapper.class).getData();
    }

    private String buildSearchUrl(AnimeSearchRequest animeSearchRequest) {
        StringBuilder searchUrl = new StringBuilder(url)
            .append("?");

        addToUrlIfPresent(searchUrl, "q", animeSearchRequest.getTitle());
        addToUrlIfPresent(searchUrl, "type", animeSearchRequest.getType());
        addToUrlIfNonEmptyList(searchUrl, "genres", animeSearchRequest.getGenres());
        addToUrlIfNonEmptyList(searchUrl, "producers", animeSearchRequest.getProducers());

        log.info("built search url: {}", searchUrl);
        return searchUrl.toString();
    }


    private void addToUrlIfPresent(StringBuilder urlBuilder, String paramName, String paramValue) {
        Optional.ofNullable(paramValue)
            .filter(value -> !value.isEmpty())
            .ifPresent(value -> urlBuilder.append('&').append(paramName).append('=').append(value));
    }

    private void addToUrlIfNonEmptyList(StringBuilder urlBuilder, String paramName, List<Integer> paramList) {
        if (!paramList.isEmpty()) {
            String paramValue = paramList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
            urlBuilder.append('&').append(paramName).append('=').append(paramValue);
        }
    }

}
