package anilist.apilistener.service.implementation;

import anilist.apilistener.model.AnimeSearchRequest;
import anilist.apilistener.service.DeserializerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeserializerServiceImpl implements DeserializerService {

    final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AnimeSearchRequest convertToAnimeSearchRequest(String json) throws JsonProcessingException {
        return mapper.readValue(json, AnimeSearchRequest.class);
    }
}
