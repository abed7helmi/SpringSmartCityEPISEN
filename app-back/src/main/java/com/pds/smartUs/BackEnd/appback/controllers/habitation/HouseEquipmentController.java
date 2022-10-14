package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.services.habitation.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path="/houseEquipment")
public class HouseEquipmentController {

    @Autowired
    private EquipmentService houseEquipmentService;



    @GetMapping("/getEquipment")
    @ResponseBody
    public List<Equipment> getHouseEquipment(@RequestParam Long idInhabitant){

        return houseEquipmentService.getHouseEquipment(idInhabitant);
    }

    @GetMapping("/sortEquipment")
    @ResponseBody
    public HashMap<String, List<Equipment>> sortHouseEquipment(@RequestParam Long idInhabitant) {
        HashMap<String, List<Equipment>> result = new HashMap<>();
        List<String> typesById = houseEquipmentService.getTypesById(idInhabitant);

        for(int i = 0; i < typesById.size(); i++) {
            result.put(typesById.get(i), houseEquipmentService.getHouseEquipmentByType(typesById.get(i), idInhabitant));
        }

        return result;
    }
}
