package com.sisd.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisd.dao.StudentDao;
import com.sisd.dtostudent.student.PageResponse;
import com.sisd.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//untuk memberi tahu eror kpd pengguna dan pembuat
@Service
public class MonitoringServicelmpl implements MonitoringService {

    @Autowired
    StudentDao studentDao;
    @Override
    public PageResponse<Student> findAll(String nisn, String studentName, String registerDate, int page,
            int size) {
                return studentDao.findAll(nisn,studentName,registerDate,page,size);
    }
    
}
