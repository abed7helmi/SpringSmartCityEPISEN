package com.pds.smartUs.BackEnd.appback.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpRoomConsumption;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpRoomConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DwpRoomConsumptionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DwpRoomConsumptionServiceTest {
    @Mock
    private DwpRoomConsumptionRepository dwpRoomConsumptionRepository;

    @InjectMocks
    private DwpRoomConsumptionService dwpRoomConsumptionService;

    @Test
    public void sumConsumptionShouldNotBeNegativeThrowException(){
        Room room = new Room();
        Float conso = dwpRoomConsumptionService.getSumConsumption(room.getId_room());
        assertThat(Math.signum(conso)).isIn(1.0f, 0.0f);
                //, anyOf(is(1.0f), is(0.0f)));
    }

    @Test(expected=NullPointerException.class)
    public void whenSaveRoomConsumptionWithNullConsumptionItShouldThrowException() {
        /*assertThrows(NullPointerException.class,
                ()->{*/
        DwpRoomConsumption dwpRoomConsumption = new DwpRoomConsumption();
        dwpRoomConsumption.setConsumption(null);
        dwpRoomConsumptionRepository.save(dwpRoomConsumption);
        fail(dwpRoomConsumption.getConsumption().toString());
                /*});*/
        //assertThat(dwpRoomConsumption).isNotNull();
    }

    @Test
    public void listAllRoomConsumptionsShouldReturnAllRoomConsumptions() {
        //GIVEN
        List<Object> roomConsumptions =dwpRoomConsumptionRepository.gestInfosConsoRooms();

        //WHEN
        List<Object> roomConsumptionsTest = dwpRoomConsumptionService.getInfosConsoRooms();

        //THEN
        Assert.assertEquals(roomConsumptions, roomConsumptionsTest);
    }

    @Test
    public void listInfosConsoRoomShouldNotBeNull() {
        List<Object> consoRoomListTest = dwpRoomConsumptionService.getInfosConsoRooms();
        Assert.assertNotNull(consoRoomListTest);
    }
}
