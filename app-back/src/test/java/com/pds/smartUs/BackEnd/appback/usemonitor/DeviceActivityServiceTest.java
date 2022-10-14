package com.pds.smartUs.BackEnd.appback.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DeviceActivity;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DeviceActivityRepository;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.DeviceActivityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DeviceActivityServiceTest {
    @Mock
    private DeviceActivityRepository deviceActivityRepository;

    @InjectMocks
    private DeviceActivityService deviceActivityService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateActivityShouldReturnTheOppositeValue() {
        DwpDevice device = new DwpDevice();
        boolean deviceStatus = deviceActivityRepository.is_active(device.getId());
        boolean newdeviceStatus = deviceActivityService.updateActivity(device.getId());
        Assert.assertEquals(newdeviceStatus, !deviceStatus);
    }

    @Test
    public void listAllActivitiesShouldReturnAllActivities() {
        //GIVEN
        List<DeviceActivity> activitiesList = (List<DeviceActivity>) deviceActivityRepository.findAll();
        //WHEN
        List<DeviceActivity> activitiesListTest = deviceActivityService.getDeviceActivities();
        //THEN
        Assert.assertEquals(activitiesList, activitiesListTest);
    }

    @Test
    public void listAllActivitiesShouldNotBeNull() {
        List<DeviceActivity> activitiesListTest = deviceActivityService.getDeviceActivities();
        Assert.assertNotNull(activitiesListTest);
    }
}
