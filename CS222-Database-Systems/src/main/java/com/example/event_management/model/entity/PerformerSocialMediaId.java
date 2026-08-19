package com.example.event_management.model.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class PerformerSocialMediaId implements Serializable {
    private Integer performerID;
    private String url;

    public PerformerSocialMediaId(){}

    public PerformerSocialMediaId(Integer performerID, String url){
        this.performerID=performerID;
        this.url=url;
    }
}
