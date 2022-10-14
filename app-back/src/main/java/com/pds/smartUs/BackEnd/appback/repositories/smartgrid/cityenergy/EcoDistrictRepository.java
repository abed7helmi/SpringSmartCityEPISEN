package com.pds.smartUs.BackEnd.appback.repositories.smartgrid.cityenergy;

import com.pds.smartUs.BackEnd.appback.entities.smartgridmix.cityenergy.EcoDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoDistrictRepository extends JpaRepository<EcoDistrict, Integer> {
}
