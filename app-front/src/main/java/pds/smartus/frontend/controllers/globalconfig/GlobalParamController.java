package pds.smartus.frontend.controllers.globalconfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/global-config")
public class GlobalParamController {

    @GetMapping("")
    public String getConfigPage() {
        return "globalconfig/global-config";
    }

}
