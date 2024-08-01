package com.loco.demo.repository.AuthenRepo;

import com.loco.demo.AuthenModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    List<User> findUsersByOnlineStatus(boolean status);
    @Query(value = "select\n" +
            "    u1_0.id,\n" +
            "    u1_0.address_id,\n" +
            "    u1_0.avatar,\n" +
            "    u1_0.birthday,\n" +
            "    u1_0.decode_password,\n" +
            "    u1_0.email,\n" +
            "    u1_0.gender,\n" +
            "    u1_0.last_online,\n" +
            "    u1_0.location,\n" +
            "    u1_0.name,\n" +
            "    u1_0.online_status,\n" +
            "    u1_0.password,\n" +
            "    u1_0.phone,\n" +
            "    u1_0.user_name,\n" +
            "    u1_0.sumary,\n" +
            "    u1_0.user_role_id\n" +
            "from (\n" +
            "    select * from (select\n" +
            "        u2.id,\n" +
            "        u2.address_id,\n" +
            "        u2.avatar,\n" +
            "        u2.birthday,\n" +
            "        u2.decode_password,\n" +
            "        u2.email,\n" +
            "        u2.gender,\n" +
            "        u2.last_online,\n" +
            "        u2.location,\n" +
            "        u2.name,\n" +
            "        u2.online_status,\n" +
            "        u2.password,\n" +
            "        u2.phone,\n" +
            "        r2.role_id as user_role_id,\n" +
            "        u2.sumary,\n" +
            "        u2.user_name\n" +
            "    from\n" +
            "        user u2\n" +
            "        join user_role_junction r2 on u2.id = r2.user_id) u\n" +
            ") u1_0\n" +
            "join role r1_0 on r1_0.role_id = u1_0.user_role_id\n" +
            "where r1_0.authority in ('admin', 'seller')", nativeQuery = true)
    List<User> findUsersByRoleAuthority();
}
