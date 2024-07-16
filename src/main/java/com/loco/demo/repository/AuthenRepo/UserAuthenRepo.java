package com.loco.demo.repository.AuthenRepo;

import com.loco.demo.AuthenModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthenRepo extends JpaRepository<User, String> {
    public Optional<User> findUserByUserId(String Id) ;
    public Optional<User> findUserByUsername(String username) ;
    public Optional<User> findAllByPassword(String password) ;
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByPhoneNumber(String phoneNumber);
}
