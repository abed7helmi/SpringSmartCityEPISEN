package com.pds.smartUs.BackEnd.appback.repositories.habitation;



import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.entities.HouseRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRoomRepository extends JpaRepository<HouseRoom,Long> {
    
//    public List<HouseRoom> findAllByHabitationIdHabitation(long idhabitation);

    public List<HouseRoom> findAllByHabitationIdHabitation(long idhabitation);
}
