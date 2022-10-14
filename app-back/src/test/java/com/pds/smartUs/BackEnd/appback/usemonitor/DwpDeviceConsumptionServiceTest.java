package com.pds.smartUs.BackEnd.appback.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DeviceActivity;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpRoomConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DwpDeviceConsumptionService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

@RunWith(MockitoJUnitRunner.class)
public class DwpDeviceConsumptionServiceTest {

    @Mock
    private DwpDeviceConsumptionRepository dwpDeviceConsumptionRepository;

    @InjectMocks
    private DwpDeviceConsumptionService dwpDeviceConsumptionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listAllDevicesConsumptionsShouldReturnAllDeviceConsumption() {
        //GIVEN
        List<DwpDeviceConsumption> consoDeviceList = (List<DwpDeviceConsumption>) dwpDeviceConsumptionRepository.findAll();
        //WHEN
        Iterable<DwpDeviceConsumption> consoDeviceListTest = dwpDeviceConsumptionService.getAllConso();
        //THEN
        Assert.assertEquals(consoDeviceList, consoDeviceListTest);
    }

    @Test(expected=NullPointerException.class)
    public void testAddConsoCafeAdd50WForItsConsumption() {
        DwpDevice device=mock(DwpDevice.class);
        DwpDeviceConsumption dwpDeviceConsumption =mock(DwpDeviceConsumption.class);
        dwpDeviceConsumption.setDevice_id(device.getId());
        Float ancienneConso = dwpDeviceConsumption.getConsumption();
        dwpDeviceConsumptionService.addConsoForCafe(device.getId());
        Assert.assertEquals(Optional.of(ancienneConso + 50), dwpDeviceConsumptionService.getConsoByDeviceId(device.getId()));
    }

    @Test
    public void listAllDevicesConsoShouldNotBeNull() {
        Iterable<DwpDeviceConsumption> consoDeviceListTest = dwpDeviceConsumptionService.getAllConso();
        Assert.assertNotNull(consoDeviceListTest);
    }

    @Test
    public void listInfosConsoDevicesShouldNotBeNull() {
        DwpDevice device=mock(DwpDevice.class);
        List<Object> consoDevicesListTest = dwpDeviceConsumptionService.getinfosDevicesbyRoom(device.getId());
        Assert.assertNotNull(consoDevicesListTest);
    }
}
