package anilist.apilistener.service;

import anilist.apilistener.model.Anime;
import java.util.List;

public interface KafkaService {

    void sendMessage(Anime data, String key);

    void sendMessage(List<Anime> data, String key);

}
