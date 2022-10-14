package pds.smartus.frontend.entities.dwp.usemonitor;

import lombok.Data;

@Data
public class DeviceActivity {
    int id;
    String date;
    boolean isActive;
    int device_id;
}
