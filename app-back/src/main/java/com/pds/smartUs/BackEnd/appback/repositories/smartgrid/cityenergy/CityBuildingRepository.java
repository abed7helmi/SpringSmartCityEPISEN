package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.CityBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityBuildingRepository extends JpaRepository<CityBuilding, Integer> {

    List<CityBuilding> getCityBuildingByDistrict_Id(int districtId);
}
