package pds.smartus.frontend.controllers.habitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.smartus.frontend.services.habitation.HabitationService;
import pds.smartus.frontend.services.habitation.HouseRoomService;

@Controller
@RequestMapping("/habitation")
public class habitationController {

    @Autowired
    private HouseRoomService houseRoomService;

    @Autowired
    private HabitationService habitationService;

    @RequestMapping(value = "/consommation/rooms",method = RequestMethod.GET)
    public String getRooms(Long habitationid,Model md) {
        md.addAttribute("rooms",houseRoomService.getRooms(habitationid));
        return "habitation/allrooms";
    }

    @RequestMapping(value = "/mecomparer",method = RequestMethod.GET)
    public String meComparer(Long habitationid) {
        return "habitation/mecomparer";
    }

    @RequestMapping(value = "/thresholdHouse",method = RequestMethod.GET)
    public String ThresholdHouse(Model md /*,@PathVariable("id_habitation") int id_habitation*/) {
        //md.addAttribute("id_habitation",habitationService.getHabitation(id_habitation));
        return "habitation/threshold";
    }


}
