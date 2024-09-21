package com.sisd.service.student;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.sisd.dtostudent.StudentRegistrationRequestDto;
// import com.sisd.entity.Student;
import com.sisd.entity.Student;


public interface RegistrationService {
    Student register(StudentRegistrationRequestDto dto);

    void uploadStudentPhoto(String studentId,MultipartFile studentPhoto)
    throws IOException, SQLException;

    void uploadParentPhoto(String studentId,MultipartFile parentPhoto)
    throws IOException, SQLException;
}
