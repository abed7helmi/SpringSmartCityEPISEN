package pds.smartus.frontend.controllers.smartgrid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/smartgrid")
public class SmartGridController {

    @GetMapping("/sites")
    public String getSitesPage() {
        return "smartgrid/sites";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage() {
        return "smartgrid/smartgrid-dashboard";
    }

    @GetMapping("/districts")
    public String getDitrictsViewPage() {
        return "smartgrid/eco-districts";
    }
}
