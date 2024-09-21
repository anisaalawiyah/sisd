package com.sisd.service.student;

import com.sisd.constant.RolesConstant;
import com.sisd.dtostudent.StudentRegistrationRequestDto;
import com.sisd.entity.Parent;
import com.sisd.entity.Roles;
import com.sisd.entity.Student;
import com.sisd.entity.Users;
import com.sisd.repository.ParentRepository;
import com.sisd.repository.RolesRepository;
import com.sisd.repository.StudentRepository;
import com.sisd.repository.UsersRepository;
import com.sisd.service.EmailSevice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.sql.rowset.serial.SerialBlob;
@Service
public class RegistrationServiceImpl implements RegistrationService{
    @Autowired
    EmailSevice emailSevice;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    @Transactional
public Student register(StudentRegistrationRequestDto dto){
        Users user = saveUsers(dto);
        Parent parent =saveParent(dto,user);
        Student student = SaveStudent(dto,parent);
        sendEmail(dto.getEmail());
        return student;

}
private Users saveUsers(StudentRegistrationRequestDto dto) {
//        create users
        Users users = new Users();
        users.setUserName(dto.getEmail());
//        generate default password (spring security)
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
//        get role for parent
        Roles parentRoles = rolesRepository.findByRoleName(RolesConstant.PARENT_ROLE);
        users.setRoles(parentRoles);
        return usersRepository.save(users);
}
//        create parent
private Parent saveParent(StudentRegistrationRequestDto dto,Users users){
        Parent parent = new Parent();
        parent.setParentName(dto.getParentName());
        parent.setAddress(dto.getAddress());
        parent.setEmail(dto.getEmail());
        parent.setPhoneNumber(dto.getPhoneNumber());
        parent.setProfession(dto.getProfession());
        parent.setUsers(users);
        return parentRepository.save(parent);
}
//        create student
private Student SaveStudent(StudentRegistrationRequestDto dto,Parent parent){
        Student student = new Student();
        student.setStudentName(dto.getStudentName());
        LocalDate dateOfBirth = LocalDate.parse(dto.getDateOfBirth(),
                DateTimeFormatter.ISO_DATE);
        student.setDateOfBirth(dateOfBirth);
        student.setPlaceOfBirth(dto.getPlaceOfBirth());
        student.setGender(dto.getGender());
        student.setRegisterDate(LocalDate.now());
        student.setParent(parent);
        return studentRepository.save(student);
}
//        ketika parent register dia dapt user, dia bakal kirim email ke parent, isi : users

private void sendEmail(String to){
        String subject = "Student Registration";
        String text = "Teruntuk Orang Tua Siswa, \n" +
                "Proses Pendaftaran siswa sudah kami terima, " +
                "selanjutnya kami akan informasikan secaaara bertahap. \n \n" +
                "Terima Kasih \n \n" +
                "Hormat, \n" +
                "Kepala Sekolah";

        emailSevice.sendSimpleMessage(to,subject,text);
        
}
@Override
    public void uploadStudentPhoto(String studentId, MultipartFile studentPhoto)
        throws IOException, SQLException{
        String[] filename = Objects.requireNonNull(studentPhoto
        .getOriginalFilename())
        .split("//.");

        if(!filename[filename.length-1].equalsIgnoreCase("jpg") &&
            !filename[filename.length-1].equalsIgnoreCase("jpeg")&&
            !filename[filename.length-1].equalsIgnoreCase("png")){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unsopperted File Type");

            }
            Student student = studentRepository.findById(studentId).orElse(null);
            if(student != null){
                student.setPhoto(new SerialBlob(studentPhoto.getBytes()));
                studentRepository.save(student);
            } else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "student not found");
       }
}

       @Override
       public void uploadParentPhoto(String parentId, MultipartFile parentPhoto)
           throws IOException, SQLException{
               Parent parent = parentRepository.findById(parentId).orElse(null);
               if(parent != null){
                   parent.setPhoto(new SerialBlob(parentPhoto.getBytes()));
                   parentRepository.save(parent);
               } else{
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "parent not found");
          }
   }

}

