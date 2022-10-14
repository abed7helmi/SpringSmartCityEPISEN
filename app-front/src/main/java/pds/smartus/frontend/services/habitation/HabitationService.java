package pds.smartus.frontend.services.habitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.habitation.Habitation;
import pds.smartus.frontend.entities.habitation.Inhabitant;
import pds.smartus.frontend.repositories.habitation.HabitationProxy;
import pds.smartus.frontend.repositories.habitation.ThresholdHabitationProxy;

@Service
public class HabitationService {

    @Autowired
    private HabitationProxy habitationProxy;
    private ThresholdHabitationProxy thesholdProxy;

    public Habitation getHabitation(int id){
        return habitationProxy.getHabitation(id);
    }

    public Habitation thresholdHabitation(){
        return thesholdProxy.getHabitation();
    }

    public Long getidhabitation(Long iduser){
        return habitationProxy.getidhabitation(iduser);
    }
    public Inhabitant getInhabitantCnx(String email,String password){
        return habitationProxy.getInhabitantCnx(email,password);
    }
}
