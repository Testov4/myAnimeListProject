package anilist.apilistener.kafka.consumer;

import anilist.apilistener.model.Anime;
import anilist.apilistener.model.AnimeSearchRequest;
import anilist.apilistener.service.AnimeService;
import anilist.apilistener.service.DeserializerService;
import anilist.apilistener.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.topic.name.consume}")
public class AnimeSearchConsumer {

    private final AnimeService animeService;

    private final DeserializerService deserializerService;

    private final KafkaService kafkaService;

    @KafkaHandler
    void listenAnimeSearchRequest(String data) throws JsonProcessingException {
        log.info("Listener received: {}", data);
        AnimeSearchRequest request = deserializerService.deserializeAnimeSearchRequest(data);
        log.info("Data deserialized: {}", request);
        String animeListJson = animeService.requestAnimeListByParameters(request);
        log.info("List from API received: {}", animeListJson);
        List<Anime> animeList = deserializerService.deserializeAnimeList(animeListJson);
        kafkaService.sendMessage(animeList);
        log.info("Message sent: {}", animeList);
    }
}
