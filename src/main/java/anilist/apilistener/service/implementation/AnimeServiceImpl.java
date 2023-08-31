package anilist.apilistener.service.implementation;

import anilist.apilistener.model.AnimeSearchRequest;
import anilist.apilistener.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimeServiceImpl implements AnimeService {

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Override
    public String requestAnimeListByParameters(AnimeSearchRequest animeSearchRequest) {
        return restTemplate.getForObject(buildSearchUrl(animeSearchRequest), String.class);
    }

    private String buildSearchUrl(AnimeSearchRequest animeSearchRequest) {
        StringBuilder searchUrl = new StringBuilder(apiUrl)
            .append("?")
            .append("&q=")
            .append(animeSearchRequest.getTitle())
            .append("&genres=")
            .append(animeSearchRequest.getGenres()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")))
            .append("&producers=")
            .append(animeSearchRequest.getProducers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        if (!animeSearchRequest.getType().isEmpty()) {
            searchUrl.append("&type=").append(animeSearchRequest.getType());
        }

        log.info("built request url: {}", searchUrl);
        return searchUrl.toString();
    }

}
