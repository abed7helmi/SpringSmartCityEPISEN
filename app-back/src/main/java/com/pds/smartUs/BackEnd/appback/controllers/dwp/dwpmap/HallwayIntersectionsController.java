package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.models.mapAlgorithm.TreeNode;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.HallwayIntersectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HallwayIntersectionsController {
    private final HallwayIntersectionsService hallwayIntersectionService;

    @Autowired
    public HallwayIntersectionsController(HallwayIntersectionsService hallwayIntersectionService) {
        this.hallwayIntersectionService = hallwayIntersectionService;
    }
    @GetMapping("/dwppath/{id_room_from}/{id_room_to}")
    public List<TreeNode> getIdRoomByArea(@PathVariable("id_room_from") int id_room_from, @PathVariable("id_room_to") int id_room_to) {
        return hallwayIntersectionService.getPath(id_room_from,id_room_to);
    }

}
