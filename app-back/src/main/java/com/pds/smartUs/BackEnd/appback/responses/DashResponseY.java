package com.pds.smartUs.BackEnd.appback.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashResponseY {
    private String name;
    private List<Double> data;

}
