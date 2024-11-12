package com.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @Column(name = "is_enabled")
    private boolean Enabled;

    @Column(name = "account_No_Expired")
    private boolean accountNonExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNonLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<RolesEntity> roles=new HashSet<>();
}
