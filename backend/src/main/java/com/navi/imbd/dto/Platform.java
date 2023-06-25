package com.navi.imbd.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Platform {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String link;

    @JsonManagedReference
    @OneToMany(mappedBy="platform", fetch=FetchType.LAZY)
    private List<Movie> movie;

}
