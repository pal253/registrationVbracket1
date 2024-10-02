package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotfFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.coyote.http11.Constants.a;

@Service
public class RegistrationService {
    private RegistrationRepository rr;
    private ModelMapper modelMapper;


    public RegistrationService(RegistrationRepository rr, ModelMapper modelMapper) {
        this.rr = rr;

        this.modelMapper = modelMapper;
    }
    public List <RegistrationDto> getRegistrations(){
        List<Registration> reg = rr.findAll();
        List <RegistrationDto> dtos = reg.stream().map(e->mapToDto(e)).collect(Collectors.toList());

        return dtos;
    }
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
       Registration registration = mapToEntity(registrationDto);

      Registration savedEntity =  rr.save(registration);
    RegistrationDto dto = mapToDto(savedEntity);
    return dto;
    }
    public void deleteRegistration(long id){
        rr.deleteById(id);
    }
    public Registration updateRegistrations(long id , Registration registration){

      Registration r =  rr.findById(id).get();
        r.setId(id);
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = rr.save(r);
        return savedEntity;
    }
    Registration mapToEntity(RegistrationDto dto){
       Registration registration = modelMapper.map(dto , Registration.class);
       // Registration registration = new Registration();
      //  registration.setName(dto.getName());
       // registration.setEmail(dto.getEmail());
      //  registration.setMobile(dto.getMobile());
        return registration;
    }
RegistrationDto mapToDto(Registration registration){
    RegistrationDto dto = modelMapper.map(registration , RegistrationDto.class);
       // RegistrationDto dto = new RegistrationDto();
      //  dto.setName(registration.getName());
      //  dto.setEmail(registration.getEmail());
      //  dto.setMobile(registration.getMobile());
        return dto;
}
public RegistrationDto getRegById(long id){
      Registration registration =  rr.findById(id)
              .orElseThrow(
    ()->new ResourceNotfFoundException("Record Not Found")
              );
     return mapToDto(registration);
}
}
