package com.pds.smartUs.BackEnd.appback.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
public class Producteur implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maker;
    private String model;
    private String type;
    private String power;
    private String state;

    @ManyToOne()
    private Bepos bepos;

    @OneToMany(mappedBy = "producteur", fetch = FetchType.LAZY)
    private List<Production> productions;


    @Override
    public String toString() {
        return "Producteur{" +
                "id=" + id +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", power='" + power + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
