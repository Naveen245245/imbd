package com.navi.imbd.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Movie {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Platform platform;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private boolean activate;
    @CreationTimestamp
    private Date created;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private float rating;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY)
    private List<Review> reviews;

}
