package com.example.odoo.repository;

import com.example.odoo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    public UserModel findByEmail(String email);
}
