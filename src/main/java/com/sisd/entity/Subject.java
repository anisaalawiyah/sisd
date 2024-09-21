package com.sisd.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_type", length = 50)
    private String subjectType;

    @Column(name = "duration")
    private Integer duration;


}

