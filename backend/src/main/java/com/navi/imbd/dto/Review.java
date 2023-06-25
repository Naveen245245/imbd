package com.navi.imbd.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Review {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ReadOnlyProperty
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    @ManyToOne
    private Movie movie;

    private String description;
    @Max(value = 10)
    @NonNull
    private float rating;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    private Date created;

}
