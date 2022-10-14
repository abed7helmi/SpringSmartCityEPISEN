package com.pds.smartUs.BackEnd.appback.controllers.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.Employee;
import com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void registerNewEmployee(@RequestBody Employee employee){
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") final Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                              @PathVariable("employeeId") int employeeId){
        employeeService.updateEmployee(employeeId, employee.getFirstname(), employee.getLastname() );
    }

}
