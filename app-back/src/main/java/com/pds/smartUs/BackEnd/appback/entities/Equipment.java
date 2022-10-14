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
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_house_equipment")
    private Long id;
    @Column(name = "equipment_type")
    private String type;
    @Column(name = "equipment_name")
    private String name;
    @Column(name = "equipment_power")
    private String power;


    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EquipmentConsomption> equipmentConsomptions = new ArrayList<>();

    @ManyToOne()
    private HouseRoom houseroom;



}
