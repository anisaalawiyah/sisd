package com.sisd.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36, nullable=false)
    private String id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc", length = 1000)
    private String roleDesc;
}
