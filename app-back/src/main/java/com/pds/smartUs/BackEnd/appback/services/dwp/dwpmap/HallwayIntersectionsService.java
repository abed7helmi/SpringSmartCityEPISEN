package com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Hallway_Intersections;
import com.pds.smartUs.BackEnd.appback.models.mapAlgorithm.HallwayAlgorithm;
import com.pds.smartUs.BackEnd.appback.models.mapAlgorithm.TreeNode;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.HallwayIntersectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class HallwayIntersectionsService {
    private final HallwayIntersectionsRepository hallWayIntersectionRepository;
    private final DWPRoomService dwpRoomService;
    public static List<Integer> list_id_of_crossedIntersections;
    @Autowired
    public HallwayIntersectionsService(HallwayIntersectionsRepository hallWayIntersectionRepository, DWPRoomService dwpRoomService) {
        this.hallWayIntersectionRepository = hallWayIntersectionRepository;
        this.dwpRoomService = dwpRoomService;
    }

    public List<TreeNode> getPath(int id_room_from, int id_room_to){
        int id_hallway_from = dwpRoomService.getDWPRoomByIdRoom(id_room_from).getId_hallway();
        int id_hallway_to = dwpRoomService.getDWPRoomByIdRoom(id_room_to).getId_hallway();
        list_id_of_crossedIntersections= new ArrayList<>();
        Hallway_Intersections intersection = hallWayIntersectionRepository.getIdHallway(id_hallway_from);
        TreeNode root = new TreeNode("H"+intersection.getId_hallway(),"origin","origin", intersection.getId_hallway(),0,0);
        List<TreeNode> firstLevel= new LinkedList<>();
        firstLevel.add(root);
        buildTree2(firstLevel);
        HallwayAlgorithm.init();

        List<TreeNode> path= new LinkedList<>();
        path.add(root);
        HallwayAlgorithm.findPaths(root,path,"H"+id_hallway_to);
        HallwayAlgorithm.printNAryTree(root);
        for(int n:HallwayAlgorithm.paths.keySet()){
            System.out.println("\n--");
            for(TreeNode node: HallwayAlgorithm.paths.get(n)){
                System.out.print( node.getIntersection()+" -> "+node.getVal()+ " -> ");
            }
        }
        List<TreeNode> best_path= HallwayAlgorithm.getBestPath();
        if(best_path.size()>1) {
            intersection = hallWayIntersectionRepository.getIntersectionByIdHallwayAndIntersection(best_path.get(0).getId_hallway(), best_path.get(1).getIntersection());
            best_path.get(0).setIntersection(intersection.getLabel());
            best_path.get(0).setDirection(intersection.getDirection());
        }
        return best_path;
    }

    public void buildTree(TreeNode node){

        List<Hallway_Intersections> hallway_intersectionsList =
                hallWayIntersectionRepository.getIdHallwayByList(node.getId_hallway());

       /* for(int i=0;i<hallway_intersectionsList.size();i++){
            list_id_of_crossedIntersections.add(hallway_intersectionsList.get(i).getId_intersection());
        }*/
        for(Hallway_Intersections intersection: hallway_intersectionsList){

            list_id_of_crossedIntersections.add(intersection.getId_intersection());

            List<Hallway_Intersections> hallway_intersectionsListToAdd =
                    hallWayIntersectionRepository.getIdIntersection(intersection.getId_intersection(),node.getId_hallway());

            for(Hallway_Intersections intersectionToAdd: hallway_intersectionsListToAdd){
                TreeNode child =  new TreeNode("H"+intersectionToAdd.getId_hallway(),intersectionToAdd.getLabel()
                                                ,intersectionToAdd.getDirection(),intersectionToAdd.getId_hallway(), intersection.getX(),
                                                  intersection.getY());

                node.addChild(child);
                buildTree(child);
            }

        }
    }

    public void buildTree2(List<TreeNode> nLevel){

        if(nLevel.isEmpty()){
            return;
        }

        List<TreeNode> newLevel = new LinkedList<>();
        for(TreeNode node: nLevel) {
            List<Hallway_Intersections> hallway_intersectionsList =
                    hallWayIntersectionRepository.getIdHallwayByList(node.getId_hallway());

            for (Hallway_Intersections intersection : hallway_intersectionsList) {

                list_id_of_crossedIntersections.add(intersection.getId_intersection());

                List<Hallway_Intersections> hallway_intersectionsListToAdd =
                        hallWayIntersectionRepository.getIdIntersection(intersection.getId_intersection(), node.getId_hallway());

                for (Hallway_Intersections intersectionToAdd : hallway_intersectionsListToAdd) {
                    TreeNode child = new TreeNode("H" + intersectionToAdd.getId_hallway(), intersectionToAdd.getLabel()
                            , intersectionToAdd.getDirection(), intersectionToAdd.getId_hallway(), intersectionToAdd.getX(),
                            intersectionToAdd.getY());

                    node.addChild(child);
                    newLevel.add(child);
                }
            }
        }
        buildTree2(newLevel);

    }


}
