package com.sisd.service.student;

import com.sisd.dtostudent.student.PageResponse;
import com.sisd.entity.Student;

public interface MonitoringService {
    PageResponse<Student> findAll(String nisn,String studentName, String registerDate,
    int page, int size);
    
}
