package com.crm.controller;
import com.crm.Payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    //http://localhost:8080/api/v1/employee/add
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeDto employeeDto=employeeService.addEmployee(dto);

        return  new ResponseEntity<>(employeeDto, HttpStatus.CREATED);

    }




    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id ) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
        // http://localhost:8080/api/v1/employee?id=1

    }



    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(@RequestParam Long id , @RequestBody EmployeeDto dto ){
        //get the employee
       EmployeeDto vd= employeeService.updateEmployee(id,dto);

        return new ResponseEntity<>(vd, HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/employee?pagesize=5 & pageNo=1
    @GetMapping()
    public ResponseEntity<List> getEmployees(@RequestParam(name = "pageSize",required = false,defaultValue = "3") int pageSize,
                                             @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
                                             @RequestParam(name="sortBy",required = false,defaultValue="name")String sortBy,
                                             @RequestParam(name="sortDir",required = false,defaultValue="asc")String sortDir
    ){
        // you can add validation for pagesize and pageNo here
        List<EmployeeDto> employeeDto =employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);


        return  new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
