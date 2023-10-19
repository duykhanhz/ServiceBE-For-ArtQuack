package com.duykhanhz.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private boolean status;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "User_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Role_roleId",referencedColumnName = "id"))

    private List<Role> roles = new ArrayList<>();
}
