package com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Hallway_Intersections;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.HallwayIntersectionsService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HallwayIntersectionsRepositoryImpl implements HallwayIntersectionsRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Hallway_Intersections> getIdHallwayByList(int id_hallway) {
       String sqlQuery="SELECT * FROM hallway_intersections WHERE id_hallway="+id_hallway;
       List<Integer> list_id_of_crossedIntersections= HallwayIntersectionsService.list_id_of_crossedIntersections;
       if(!list_id_of_crossedIntersections.isEmpty()){
           sqlQuery+=" AND id_intersection NOT IN (";
           for(int i=0;i<list_id_of_crossedIntersections.size();i++){

               if(i!=0){ sqlQuery+=","; }
               sqlQuery+= list_id_of_crossedIntersections.get(i);

           }
           sqlQuery+=")";
       }
        Query query = entityManager.createNativeQuery(sqlQuery,
                Hallway_Intersections.class);

        return query.getResultList();
    }

    @Override
    public List<Hallway_Intersections> getIdIntersection(int id_intersection, int id_of_crossedHallway) {

        Query query = entityManager.createNativeQuery
                ("SELECT * FROM hallway_intersections WHERE id_intersection="+id_intersection+
                        " AND id_hallway!="+id_of_crossedHallway,
                        Hallway_Intersections.class);

        return query.getResultList();
    }

    public Hallway_Intersections getIdHallway(int id_hallway) {

        Query query = entityManager.createNativeQuery("SELECT * FROM hallway_intersections WHERE id_hallway="+id_hallway,
        Hallway_Intersections.class);


        return (Hallway_Intersections) query.getResultList().get(0);
    }

    @Override
    public Hallway_Intersections getIntersectionByIdHallwayAndIntersection(int id_hallway, String intersection) {
        Query query = entityManager.createNativeQuery("SELECT * FROM hallway_intersections WHERE id_hallway="+id_hallway+
                                                         " AND label='"+intersection+"'",Hallway_Intersections.class);
        return (Hallway_Intersections) query.getResultList().get(0);
    }

}
