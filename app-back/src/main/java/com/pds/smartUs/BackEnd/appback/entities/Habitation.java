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
@Table(name = "habitation2")
@Builder
public class Habitation  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long idHabitation;
    @Column(name = "zip_code")
    int zipCode;
    @Column(name = "street_name")
    String streetName;
    @Column(name = "street_number")
    String streetNumber;
    @Column(name = "habitation_type")
    String HabitationType;
    @Column(name = "is_active")
    boolean isActive;

    @OneToMany(mappedBy = "habitation", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HouseRoom> houseRooms = new ArrayList<>();

    @ManyToOne()
    private Bepos bepos;

    @OneToOne(mappedBy = "habitation")
    private Inhabitant inhabitant;



}
