package pds.smartus.frontend.controllers.habitation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pds.smartus.frontend.entities.habitation.Habitation;
import pds.smartus.frontend.entities.habitation.Inhabitant;
import pds.smartus.frontend.services.habitation.HabitationService;
import pds.smartus.frontend.services.habitation.ProducteurService;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/habitation")
public class habitantController {

    @Autowired
    private ProducteurService producteurService;

    @Autowired
    private HabitationService habitationService;

    @RequestMapping(value = "/producteurs",method = RequestMethod.GET)
    public String getProducteurs(Model md ,Long beposid) {
        md.addAttribute("producteurs",producteurService.getProducteurs(beposid));
        return "habitation/producteurs";
    }




    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public String saveUser(Model md) {
        return "habitation/producteurs";
    }

    @GetMapping()
    public String getPage(Model md,@RequestParam(name="idhabitation") int idhabitation,HttpSession httpSession) {
        Habitation habitation=habitationService.getHabitation(idhabitation);

        httpSession.setAttribute("idhabitation",idhabitation);
        httpSession.setAttribute("beposid",habitation.getBepos().getId());

        md.addAttribute("habitation",habitation);

        return "habitation/accueil";
    }




    @GetMapping("/Auth")
    public String login(HttpSession httpSession) {
        httpSession.setAttribute("connected",false);
        return "habitation/login";
    }



    @GetMapping("/checkUser")
    public String checkUser(@RequestParam(name = "mail") String mail,@RequestParam(name = "password") String password, Model md, HttpSession httpSession) {

        Inhabitant verfiUser = habitationService.getInhabitantCnx(mail,password);
        if (verfiUser !=null){
            int idhabitation = Math.toIntExact(habitationService.getidhabitation(verfiUser.getIdInhabitant()));
            httpSession.setAttribute("connected",true);
            md.addAttribute("idhabitation",idhabitation);
            return "habitation/loginsucc";

        }else {
            return "habitation/loginfaild";
        }

    }



}
