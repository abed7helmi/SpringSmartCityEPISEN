package com.pds.smartUs.BackEnd.appback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bepos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adresse;

    @OneToMany(mappedBy = "bepos", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Habitation> habitations = new ArrayList<>();


    @OneToMany(mappedBy = "bepos", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Producteur> producteurs = new ArrayList<>();

    @OneToMany(mappedBy = "bepos", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alert> alerts = new ArrayList<>();

}
