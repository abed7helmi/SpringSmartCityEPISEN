package com.pds.smartUs.BackEnd.appback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="inhabitant2")
@Builder
public class Inhabitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inhabitant")
    Long idInhabitant;
    @Column(name = "inhabitant_first_name")
    String inhabitantFirstName;
    @Column(name = "inhabitant_last_name")
    String inhabitantLastName;
    @Column(name = "inhabitant_email")
    String inhabitantEmail;
    @Column(name = "inhabitant_password")
    String inhabitantPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "habitation_id", referencedColumnName = "idHabitation")
    @JsonIgnore
    private Habitation habitation;

}