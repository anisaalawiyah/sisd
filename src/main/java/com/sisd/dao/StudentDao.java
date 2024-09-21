package com.sisd.dao;

import com.sisd.dtostudent.student.PageResponse;
import com.sisd.entity.Student;

public interface StudentDao {
    PageResponse<Student> findAll(String nisn, String studentName,
                        String registerDate,int page ,int size);
}