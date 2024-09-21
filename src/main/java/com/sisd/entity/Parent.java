package com.sisd.entity;

import java.sql.Blob;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "address", length = 2000)
    private String address;

    @Column(name = "profession")
    private String profession;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @OneToOne
    @JoinColumn(name="users_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Users users;
}

