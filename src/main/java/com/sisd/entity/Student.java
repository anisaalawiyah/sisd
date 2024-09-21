package com.sisd.entity;

import java.sql.Blob;
import java.time.LocalDate;
// import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "nisn", length = 25)
    private String nisn;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "dat_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth", length = 100)
    private String placeOfBirth;

    @Column(name = "gender", length = 25)
    private String gender;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @OneToOne
    @JoinColumn(name="parent_id", referencedColumnName = "id", nullable = false)
    private Parent parent;

    
}

