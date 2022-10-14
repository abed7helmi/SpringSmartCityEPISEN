package com.pds.smartUs.BackEnd.appback.controllers.dwp.dwpmap;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.DWP;
import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.DWPService;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DWPController {


    private final DWPService dwpService;

    @Autowired
    public DWPController(DWPService dwpMapService) {
        this.dwpService=dwpMapService;
    }

    @GetMapping("/dwp/{id_dwp}")
    public DWP getDWP(@PathVariable int id_dwp) {
        return dwpService.getDWPById(id_dwp);
     }

     @GetMapping("/dwp/building/{id_building}")
     public List<DWP> getDWPByIdBuilding(@PathVariable("id_building") int id_building){
        return dwpService.getDWPByIdBuilding(id_building);
     }
    @GetMapping("/dwp/building/{id_building}/{configured}")
    public List<DWP> getDWPByIdBuilding(@PathVariable("id_building") int id_building,
                                        @PathVariable("configured") boolean configured){
        return dwpService.getDWPByIdBuilding(id_building,configured);
    }

     @PutMapping("/{id_dwp}/{bool}")
    public void updateConfigured(@PathVariable("bool") boolean value, @PathVariable("id_dwp") int id_dwp) {
        dwpService.updateConfigured(value, id_dwp);
     }


}
