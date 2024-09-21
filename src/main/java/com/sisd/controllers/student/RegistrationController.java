package com.sisd.controllers.student;

import com.sisd.dtostudent.GenericResponse;
import com.sisd.dtostudent.StudentRegistrationRequestDto;
import com.sisd.entity.Student;
import com.sisd.service.student.RegistrationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.Multipart;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/student")
@Tag(name="student")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody StudentRegistrationRequestDto dto){
        try{
            Student student =registrationService.register(dto);
            return ResponseEntity.ok()
            .body(GenericResponse.success(student,
        "Successfully registrasi new student"));
        }catch(Exception e){
            return ResponseEntity.internalServerError()
            .body(GenericResponse.eror(e.getMessage()));

        }

    }
    
    @PostMapping(value="/upload-student-photo",
    consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadStudentPhoto(@RequestParam String student,
    @RequestParam("studentPhoto") MultipartFile file){
        try{
            return ResponseEntity.ok()
            .body(GenericResponse.success(null,"Successfuly upload photo"));
        }catch(ResponseStatusException e){
            log.info(e.getMessage());
            return ResponseEntity.status(e.getStatusCode())
                .body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            log.info(e.getMessage());
            return ResponseEntity.internalServerError()
            .body(GenericResponse.eror(MassageConstant.ERROR_500));
        }
    }

    @PostMapping(value="/upload-parent-photo",
    consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadParentPhoto(@RequestParam String student,
    @RequestParam("Parent photo") Multipart file){
        try{

            return ResponseEntity.ok()
            .body(GenericResponse.success(null,"Successfuly upload photo"));
        }catch(ResponseStatusException e){
            log.info(e.getMessage());
            return ResponseEntity.status(e.getStatusCode())
                .body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            log.info(e.getMessage());
            return ResponseEntity.internalServerError()
            .body(GenericResponse.eror(MassageConstant.ERROR_500));
        }
    }
}
