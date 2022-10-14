package com.pds.smartUs.BackEnd.appback.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
public class Production implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    private double production;

    @ManyToOne()
    @JsonBackReference
    private Producteur producteur;

}
