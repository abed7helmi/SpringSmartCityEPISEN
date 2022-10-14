package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;

import java.util.List;

public interface DWPRepositoryCustom {
    List<DWP> getDWPByIdBuilding(int id_building);
    List<DWP> getDWPByIdBuilding(int id_building,boolean configured);

}
