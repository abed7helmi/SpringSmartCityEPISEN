package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.services.dwp.dwpmap.RoomService;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DwpDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/devices")
public class DwpDeviceController {
    private final DwpDeviceService dwpDeviceService;

    @Autowired
    public DwpDeviceController(DwpDeviceService dwpDeviceService) {
        this.dwpDeviceService = dwpDeviceService;
    }

    @GetMapping
    public Iterable<DwpDevice> getAllEquipment(){
        return dwpDeviceService.getEquipments();
    }

    @GetMapping(path = "/room/{idroom}")
    public Iterable<DwpDevice> getAllEquipmentbyRoom(@PathVariable("idroom") int idroom){
        return dwpDeviceService.getEquipmentsbyRoom(idroom);
    }

    @GetMapping(path = "/PC/{idroom}")
    public int getPC(@PathVariable("idroom") int idroom){
        return dwpDeviceService.getPC(idroom);
    }

    @GetMapping(path = "/{equipmentId}")
    public DwpDevice getEquipmentById(@PathVariable("equipmentId") int equipmentId){
        return dwpDeviceService.getEquipmentById(equipmentId);
    }

    @PostMapping
    public void registerNewEquipment(@RequestBody DwpDevice dwpDevice){
        dwpDeviceService.addNewEquipment(dwpDevice);
    }

    @DeleteMapping("{id}")
    public void deleteEquipment(@PathVariable("id") final Integer id) {
        dwpDeviceService.deleteEquipment(id);
    }



}

