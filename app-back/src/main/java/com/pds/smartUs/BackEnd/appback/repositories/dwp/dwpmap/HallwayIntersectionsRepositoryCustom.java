package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Hallway_Intersections;

import java.util.List;

public interface HallwayIntersectionsRepositoryCustom {

    List<Hallway_Intersections> getIdHallwayByList(int id_hallway);
    List<Hallway_Intersections> getIdIntersection(int id_intersection, int id_of_crossedHallway);
    Hallway_Intersections getIdHallway(int id_hallway);

    Hallway_Intersections getIntersectionByIdHallwayAndIntersection(int id_hallway, String intersection);
}
