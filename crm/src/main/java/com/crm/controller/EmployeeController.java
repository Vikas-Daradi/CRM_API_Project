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

        /*जब क्लाइंट /add Endpoint पर POST रिक्वेस्ट भेजता है,
         तो @Valid डेटा की वैधता चेक करता है।
अगर डेटा वैध नहीं है, तो Error Message और Status Code 500 रिटर्न होता है।
अगर डेटा वैध है, तो employeeService.addEmployee(dto) द्वारा डेटा डाटाबेस में सेव होता है।
सेव किया गया डेटा और Status Code 201 रिटर्न होता है।*/
    }




    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id ) {
        // TODO Auto-generated method stub
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("deleted",HttpStatus.OK);
        // http://localhost:8080/api/v1/employee?id=1

    }



    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(@RequestParam Long id , @RequestBody EmployeeDto dto ){
        //get the employee
       EmployeeDto employeeDto= employeeService.updateEmployee(id,dto);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
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
/*Postman: Sends a POST request with JSON body to the /api/users endpoint.
Controller: Handles the request and calls the createUser() method of the Service layer.
Service: Converts the DTO into an Entity, saves it via the Repository, and converts the saved Entity back into a DTO.
Repository: Saves the Entity to the database.
Database: Stores the record in the users table.
Response: The Controller sends a 201 Created response with the created UserDTO back to Postman.
*/