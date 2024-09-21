package com.sisd.dtostudent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistrationRequestDto {
//    student
    String studentName;
    String dateOfBirth; //2002-10-25 yyyy-MM-dd ISO_8601
    String placeOfBirth;
    String gender;
    String studentPhoto;


//    parent
    String parentName;
    String address;
    String email;
    String phoneNumber;
    String profession;
    String parentsPhoto;
    String password;


}
