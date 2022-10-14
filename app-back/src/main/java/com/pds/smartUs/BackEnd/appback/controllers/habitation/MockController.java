package com.pds.smartUs.BackEnd.appback.controllers.habitation;

import com.pds.smartUs.BackEnd.appback.services.habitation.mock.MockServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mock")
public class MockController {

    private MockServices mockServices;

    public MockController(MockServices mockServices) {
        this.mockServices = mockServices;
    }

    @GetMapping("/mockParameters")
    public void mockParameters(@RequestParam int scenario) throws InterruptedException {
        System.out.println("etape 1");
        mockServices.dayParametersMock(scenario);
    }
}
