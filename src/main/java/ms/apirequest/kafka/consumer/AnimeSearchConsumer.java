package ms.apirequest.kafka.consumer;

import ms.apirequest.exception.ApiErrorResponseException;
import ms.apirequest.exception.WrongRequestFormatException;
import ms.apirequest.model.Anime;
import ms.apirequest.service.AnimeService;
import ms.apirequest.service.DeserializerService;
import ms.apirequest.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.topic.name.consume}")
public class AnimeSearchConsumer {

    private final AnimeService animeService;

    private final DeserializerService deserializerService;

    private final KafkaService kafkaService;

    @KafkaHandler
    void listenAnimeSearchRequest(String data) {
        try {
            Map request = deserializerService.deserializeAnimeSearchRequest(data);
            log.info("Data deserialized: {}", request);
            List<Anime> animeList = animeService.requestAnimeListByParameters(request);
            log.info("List from API received: {}", animeList);
            kafkaService.sendMessage(animeList);
            log.info("Message sent: {}", animeList);
        } catch (WrongRequestFormatException e) {
            log.error("Failed to process message: {}", e.getMessage());
        } catch (ApiErrorResponseException e) {
            log.error("Api response is not valid: {}", e.getMessage());
        }
    }
}
