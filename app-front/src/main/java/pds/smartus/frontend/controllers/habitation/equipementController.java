package pds.smartus.frontend.controllers.habitation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/habitation/equipement")
public class equipementController {

    @RequestMapping(value = "/consomption",method = RequestMethod.GET)
    public String getProducteurs(Model md) {
        return "habitation/deviceconsomption";
    }


}
