package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityHouseRepository extends JpaRepository<CityHouse, Integer> {

    List<CityHouse> getCityHouseByDistrict_Id(int districtId);
}
