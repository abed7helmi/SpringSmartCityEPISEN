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
@Table(name = "house_room2")
@Builder
public class HouseRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    Long idRoom;
    @Column(name = "room_name")
    String roomName;
    @Column(name = "surface")
    int surface;
    @Column(name = "nb_windows")
    int nbWindows;


    @OneToMany(mappedBy = "houseroom", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Equipment> equipments = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    private Habitation habitation;
}
