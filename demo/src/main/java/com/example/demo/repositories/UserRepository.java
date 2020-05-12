package com.example.demo.repositories;

/**
 * Created by kanet on 10.01.2020.
 */
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(int id);

    // SELECT * FROM tbl_user WHERE email = :email
}