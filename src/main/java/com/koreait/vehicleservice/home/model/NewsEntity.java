package com.koreait.vehicleservice.home.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NewsEntity {
    private int newspk;
    private String title;
    private String originallink;
    private String link;
    private String description;
    private String pubDate;

    /*public void setPubDate(String pubDate) {
        int target_num = pubDate.indexOf("+");
        this.pubDate = pubDate.substring(0, target_num);
    }*/

    public void setTitle(String title) {
        this.title = title.replaceAll("<b>", "").trim();
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("<b>", "").trim();
    }
}
