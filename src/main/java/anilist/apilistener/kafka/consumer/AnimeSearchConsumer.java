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
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.topic.name.consume}", groupId = "groupId")
public class AnimeSearchConsumer {

    private final AnimeService animeService;

    private final DeserializerService deserializerService;

    private final KafkaService kafkaService;

    @KafkaHandler
    void listener(@Payload String data, @Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonProcessingException {
        log.info("Listener with key:{} received: {}", key, data);
        AnimeSearchRequest request = deserializerService.convertToAnimeSearchRequest(data);
        log.info("Data serialized: {}", request);
        List<Anime> animeList = animeService.findAnimeByParameters(request);
        log.info("List from API received: {}", Arrays.toString(animeList.toArray()));
        kafkaService.sendMessage(animeList, key);
        log.info("Message sent: {}", Arrays.toString(animeList.toArray()));
    }
}
