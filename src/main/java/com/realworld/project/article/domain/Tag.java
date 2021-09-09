package com.realworld.project.article.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag")
public class Tag {

    @Id @Column(name = "tag_id")
    @GeneratedValue
    private Long id;
    private String name;


    public Tag(String name) {
        this.name = name;
    }
}
