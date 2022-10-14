package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.Employee;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeById(int employeeId){
        return employeeRepository.findById(employeeId).get();
    }

    public void deleteEmployee(final Integer id) {
        employeeRepository.deleteById(id);
    }

    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(int employeeId, String firstname, String lastname) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Employee with id" + employeeId + "does not exist"));

        if (firstname != null && firstname.length() > 0 && !Objects.equals(employee.getFirstname(), firstname)){
            employee.setFirstname(firstname);
        }

        if (lastname != null && lastname.length() > 0 && !Objects.equals(employee.getLastname(), lastname)){
            employee.setLastname(lastname);
        }
    }
}
