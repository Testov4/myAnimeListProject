package anilist.apilistener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Studio {

    Integer mal_id;

    String type;

    String name;

    String url;

}
