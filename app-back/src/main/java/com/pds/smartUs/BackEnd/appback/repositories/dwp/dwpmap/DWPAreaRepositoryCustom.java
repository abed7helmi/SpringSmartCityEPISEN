package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP_Area;

import java.util.List;

public interface DWPAreaRepositoryCustom {

    List<DWP_Area> getAreasByArchitecture(int architecture);
}
