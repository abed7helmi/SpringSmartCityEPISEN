package com.pds.smartUs.BackEnd.appback.models;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RoomStatus {
    private Room room;
    private Boolean status;
}
