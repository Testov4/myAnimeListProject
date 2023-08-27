package anilist.apilistener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.HashMap;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Anime {
    private Integer mal_id;

    private String url;

    private HashMap<String, HashMap<String, String>> images;

    private String title;

    private String title_english;

    private String title_japanese;

    private List<String> title_synonyms;

    private String type;

    private Integer episodes;

    private String airing;

    private String status;

    private String duration;

    private Integer year;

    private List<Genre> genres;

    private List<Studio> studios;
}
