package anilist.apilistener.service.implementation;

import anilist.apilistener.model.Anime;
import anilist.apilistener.model.AnimeSearchRequest;
import anilist.apilistener.service.DeserializerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeserializerServiceImpl implements DeserializerService {

    private final ObjectMapper mapper;

    @Override
    public AnimeSearchRequest deserializeAnimeSearchRequest(String json) {
        try {
            return mapper.readValue(json, AnimeSearchRequest.class);
        } catch (JsonProcessingException e) {
            log.warn("Failed using mapper to read JSON: {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Anime> deserializeAnimeList(String json) {
        try {
            JsonNode jsonNode = mapper.readTree(json);
            if (jsonNode.hasNonNull("data")) {
                return mapper.convertValue(mapper.readTree(json).get("data"),
                    new TypeReference<>() {
                    });
            }
            else {
                log.warn("response from API is empty");
                return List.of();
            }
        } catch (JsonProcessingException e) {
            log.warn("Failed using mapper to read JSON: {}", e);
            return List.of();
        }
    }
}
