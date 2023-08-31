package anilist.apilistener.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.HashMap;
import java.util.List;

@Data
public class Anime {

    @JsonProperty("mal_id")
    private Integer malId;
    private String url;
    private HashMap<String, Image> images;
    private String title;
    @JsonProperty("title_english")
    private String titleEnglish;
    @JsonProperty("title_japanese")
    private String titleJapanese;
    @JsonProperty("title_synonyms")
    private List<String> titleSynonyms;
    private String type;
    private Integer episodes;
    private String airing;
    private String status;
    private String duration;
    private Integer year;
    private List<Genre> genres;
    private List<Studio> studios;

}
