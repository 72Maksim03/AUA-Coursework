package com.example.event_management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Performer_social_media")
@Getter
@Setter
@ToString
public class PerformerSocialMedia {
    @EmbeddedId
    private PerformerSocialMediaId id;

    @MapsId("performerID")
    @ManyToOne
    @JoinColumn(name = "PerformerID")
    @JsonIgnore
    private Performer performer;

    @Column(name = "platform", nullable = false)
    private String platform;

    public void setUrl(String url){
        id.setUrl(url);
    }

    public String getUrl(){return id.getUrl();}
}