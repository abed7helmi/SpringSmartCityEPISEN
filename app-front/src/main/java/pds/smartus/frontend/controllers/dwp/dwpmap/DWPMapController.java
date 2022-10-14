package pds.smartus.frontend.controllers.dwp.dwpmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pds.smartus.frontend.entities.dwp.dwpmap.DWPAreaForm;
import pds.smartus.frontend.entities.dwp.dwpmap.DWP_Room;
import pds.smartus.frontend.services.dwp.dwpmap.DWPMapService;

@Controller
public class DWPMapController {
    @Autowired
    private DWPMapService dwpMapService;

    @PostMapping("/configMap")
    public String getContent(@RequestParam(value = "archi", required = false) int archi,
                             @RequestParam("id_dwp") int id_dwp,@RequestParam("action") String action,
                             @RequestParam("building_name") String building_name,
                             @RequestParam("floor_name") String  floor_number,
                             Model md){
        boolean configured = dwpMapService.isConfigured(id_dwp);
        if(configured && dwpMapService.getSingleDWPRoom(id_dwp).getArchi_type() == archi){
          if(archi == 1){
              dwpMapService.buildMap1(md,id_dwp);
          }
          else{
              dwpMapService.buildMap2(md,id_dwp);
          }
            md.addAttribute("architectureStatus","oldArchitecture");
        }
        else {
            md.addAttribute("architectureStatus","newArchitecture");
        }
        md.addAttribute("building_name",building_name);
        md.addAttribute("floor_name",floor_number);
        md.addAttribute("configured",configured);
        md.addAttribute("fragment_url","fragments/dwpmap/map"+archi);
        md.addAttribute("action", action);
        md.addAttribute("id_dwp", id_dwp);
        md.addAttribute("architecture",archi);
        return "dwp/dwpmap/configMap";
    }

    @GetMapping("/dwpmap1/{action}/{building_name}/{floor_number}/{id_dwp}")
    public String getMap1(@PathVariable("id_dwp") int id_dwp,@PathVariable("action") String action,
                          @PathVariable("building_name") String building_name,
                          @PathVariable("floor_number") String  floor_number,
                          Model md){

        if(dwpMapService.isConfigured(id_dwp)){
            md.addAttribute("action",action);
            md.addAttribute("building_name",building_name);
            md.addAttribute("floor_name",floor_number);
            md.addAttribute("fragment_url","fragments/dwpmap/map1");
            dwpMapService.buildMap1(md, id_dwp);
            return "dwp/dwpmap/dwpmap";
        }
        else {
            return "redirect:/selectDWP/configMap";
        }
    }

    @GetMapping("/dwpmap2/{action}/{building_name}/{floor_number}/{id_dwp}")
    public String getMap2(@PathVariable("id_dwp") int id_dwp,@PathVariable("action") String action,
                          @PathVariable("building_name") String building_name,
                           @PathVariable("floor_number") String  floor_number,
                          Model md){
        if(dwpMapService.isConfigured(id_dwp)){
            md.addAttribute("action",action);
            md.addAttribute("building_name",building_name);
            md.addAttribute("floor_name",floor_number);
            md.addAttribute("fragment_url","fragments/dwpmap/map2");
            dwpMapService.buildMap2(md,id_dwp);
            return "dwp/dwpmap/dwpmap";
        }
        else {
            return "redirect:/selectDWP/configMap";
        }
    }

    @GetMapping("/chooseArchi")
    public String chooseArchi(@RequestParam("id_dwp") int id_dwp,
                              @RequestParam("action") String action,
                              @RequestParam("building_name") String building_name,
                              @RequestParam("floor_name") String  floor_number,Model md){

        boolean configured = dwpMapService.isConfigured(id_dwp);

        if(configured){
            int archi = dwpMapService.getSingleDWPRoom(id_dwp).getArchi_type();
            md.addAttribute("architecture",archi);
        }
            md.addAttribute("id_dwp",id_dwp);
            md.addAttribute("action",action);
            md.addAttribute("configured",configured);
            md.addAttribute("building_name",building_name);
            md.addAttribute("floor_name",floor_number);
            return "dwp/dwpmap/configuredwp";

    }

    @GetMapping("/selectDWP/{action}")
    public String selectDWP(@PathVariable("action") String action, Model md){

        md.addAttribute("action",action);
        md.addAttribute("buildingList",dwpMapService.getBuildings());
        if(action.equals("showMap") || action.equals("findPath")) {
            md.addAttribute("dwpList", dwpMapService.getDWPsByIdBuilding(1,true));
        }
        else {
            md.addAttribute("dwpList", dwpMapService.getDWPsByIdBuilding(1));
        }
        return "dwp/dwpmap/selectDWP";
    }

    @PostMapping("/dwp/action")
    public String redirectToAction(@RequestParam(name = "action") String action,
                                   @RequestParam(value = "buildings") int id_building,
                                   @RequestParam(value = "dwp") int id_dwp, Model md){
        String building_name = dwpMapService.getBuildingById(id_building).getBuilding_name();
        int floor_number = dwpMapService.getFloorNumber(id_dwp);

        if(action.equals("showMap") || action.equals("findPath")) {
            int archi = dwpMapService.getSingleDWPRoom(id_dwp).getArchi_type();
            return "redirect:/dwpmap"+archi+"/"+action+"/"+building_name+"/"+floor_number+"/"+id_dwp;
        }
        else {
            boolean configured = dwpMapService.isConfigured(id_dwp);

            if(configured){
                int archi = dwpMapService.getSingleDWPRoom(id_dwp).getArchi_type();
                md.addAttribute("architecture",archi);
            }
            md.addAttribute("action",action);
            md.addAttribute("id_dwp",id_dwp);
            md.addAttribute("configured",configured);
            md.addAttribute("building_name",building_name);
            md.addAttribute("floor_name",floor_number);
            return "dwp/dwpmap/configuredwp";
        }

    }

    @GetMapping("/deleteConfig")
    public String deleteConfig(@RequestParam(value = "id_dwp") int id_dwp){
        dwpMapService.deleteDWPContent(id_dwp);
        return "redirect:/selectDWP/configMap";
    }

    @RequestMapping(value="/startMap", method=RequestMethod.GET)
    public String getEventCount(ModelMap map) {
        dwpMapService.buildMap2(map,1);
        map.addAttribute("fragment_url1","fragments/dwpmap/map2");
        map.addAttribute("action1","show1");
        // change "myview" to the name of your view
        return "dwp/dwpmap/test :: startMap";
    }



}
