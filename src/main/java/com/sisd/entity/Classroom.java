package com.sisd.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "classroom_name")
    private String classroomName;

    @Column(name = "location", length = 2000)
    private String location;

    @Column(name="level", length = 50)
    private String level;

    @OneToOne
    @JoinColumn(name="teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;
}

