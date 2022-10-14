package pds.smartus.frontend.controllers.habitation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.smartus.frontend.services.habitation.EquipmentService;

@Controller
@RequestMapping("/habitation")
public class homeroomcontroller {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(value = "/rooms",method = RequestMethod.GET)
    public String getEquipments(Long roomid ,Model md) {
        md.addAttribute("equipments",equipmentService.getEquipments(roomid));
        return "habitation/rooms";
    }

}
