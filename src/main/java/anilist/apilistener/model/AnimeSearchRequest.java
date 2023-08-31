package anilist.apilistener.model;

import lombok.Data;
import java.util.List;

@Data
public class AnimeSearchRequest {

    private String title;
    private String type;
    private List<Integer> genres;
    private List<Integer> producers;

}
