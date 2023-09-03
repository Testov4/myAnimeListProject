package anilist.apilistener.service;

import anilist.apilistener.model.Anime;
import java.util.List;

public interface KafkaService {

    void sendMessage(List<Anime> data);

}
