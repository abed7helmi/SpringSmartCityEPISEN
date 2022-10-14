package com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dwp_device_activity")
public class DeviceActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int activity_id;

    @Column(name="is_active")
    boolean active;
    @Column(name="change_date")
    String change_date;
    @Column(name="device_id")
    int device_id;

    public DeviceActivity() {

    }

    public DeviceActivity(boolean active, String change_date, int device_id) {
        this.active = active;
        this.change_date = change_date;
        this.device_id = device_id;
    }
}
