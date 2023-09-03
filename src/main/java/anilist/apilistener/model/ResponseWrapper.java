package anilist.apilistener.model;

import lombok.Data;
import java.util.List;

@Data
public class ResponseWrapper {

    private List<Anime> data;

    private String status;

    private String type;
}
