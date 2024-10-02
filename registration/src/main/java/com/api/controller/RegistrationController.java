package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService rs;

    public RegistrationController(RegistrationService rs) {
        this.rs = rs;
    }

    @GetMapping
    public ResponseEntity <List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> dtos = rs.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity <?> CreateRegistration(
            @Valid @RequestBody RegistrationDto registrationDto ,
            BindingResult result
            )
    {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.CREATED);
        }
      RegistrationDto regdto =  rs.createRegistration(registrationDto);
      return new ResponseEntity<>(regdto , HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
        @RequestParam    long id
    ){
        rs.deleteRegistration(id);

        return new ResponseEntity<>("Deleted" , HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistrations(
            @PathVariable long id ,
            @RequestBody Registration registration
    ){
      Registration updateReg =  rs.updateRegistrations(id , registration);
return new ResponseEntity<>(updateReg , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
        RegistrationDto dto = rs.getRegById(id);
        return new ResponseEntity<>(dto ,HttpStatus.OK);

    }




}
