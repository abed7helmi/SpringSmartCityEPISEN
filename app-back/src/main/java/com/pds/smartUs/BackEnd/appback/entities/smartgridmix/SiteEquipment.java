package com.pds.smartUs.BackEnd.appback.entities.smartgridmix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site_equipment")
public class SiteEquipment {

    @Id
    @Column(name = "site_equipment_id")
    private int id;

    @Column(name = "site_equipment_name")
    private String siteEquipmentName;

    @Column(name = "equipment_prod_capacity")
    private String equipmentProdCapacity;

    @Column(name = "site_equipment_state")
    private boolean siteEquipmentState;

    @Column(name = "production_site_id")
    private int productionSiteId;

    public SiteEquipment(){
    }

    public SiteEquipment(String siteEquipmentName, String equipmentProdCapacity, boolean siteEquipmentState, int productionSiteId) {
        this.siteEquipmentName = siteEquipmentName;
        this.equipmentProdCapacity = equipmentProdCapacity;
        this.siteEquipmentState = siteEquipmentState;
        this.productionSiteId = productionSiteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiteEquipmentName() {
        return siteEquipmentName;
    }

    public void setSiteEquipmentName(String siteEquipmentName) {
        this.siteEquipmentName = siteEquipmentName;
    }

    public String getEquipmentProdCapacity() {
        return equipmentProdCapacity;
    }

    public void setEquipmentProdCapacity(String equipmentProdCapacity) {
        this.equipmentProdCapacity = equipmentProdCapacity;
    }

    public boolean isSiteEquipmentState() {
        return siteEquipmentState;
    }

    public void setSiteEquipmentState(boolean siteEquipmentState) {
        this.siteEquipmentState = siteEquipmentState;
    }

    public int getProductionSiteId() {
        return productionSiteId;
    }

    public void setProductionSiteId(int productionSiteId) {
        this.productionSiteId = productionSiteId;
    }

    @Override
    public String toString() {
        return "SiteEquipment{" +
                "id=" + id +
                ", siteEquipmentName='" + siteEquipmentName + '\'' +
                ", equipmentProdCapacity='" + equipmentProdCapacity + '\'' +
                ", siteEquipmentState=" + siteEquipmentState +
                ", productionSiteId=" + productionSiteId +
                '}';
    }
}
