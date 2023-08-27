package anilist.apilistener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class AnimeSearchRequest {

    String title;

    String type;

    List<Integer> genres;

    List<Integer> producers;

}
