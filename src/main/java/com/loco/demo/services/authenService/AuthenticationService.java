package com.loco.demo.services.authenService;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.AuthenDTO.GoogleLoginDTO;
import com.loco.demo.DTO.AuthenDTO.JSONresponse;
import com.loco.demo.DTO.AuthenDTO.LoginResponseDTO;
import com.loco.demo.DTO.JSON.RegisterServiceReturnDTO;
import com.loco.demo.DTO.JSON.TokenDTO;
import com.loco.demo.repository.AuthenRepo.RoleAuthenRepo;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import com.loco.demo.utils.Constants.Const;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class AuthenticationService {
    private final UserAuthenRepo userRepository ;
    private final RoleAuthenRepo roleRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager ;
    private final TokenService tokenService ;


    @Autowired
    public AuthenticationService(UserAuthenRepo userRepository, RoleAuthenRepo roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User registerUserTesting(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        String fakeID = UUID.randomUUID().toString();
        return userRepository.save(new User(fakeID, username, password, encodedPassword, authorities));
    }

    private Role addIntoRoleRepositoryAndReturnDefaultUserRole() {
        String fakeID = UUID.randomUUID().toString();
        Role newRole = new Role(fakeID,"USER");
        System.out.println(newRole);
        roleRepository.save(newRole) ;
        return newRole;
    }

    private LoginResponseDTO handleGoogleLoginWhenEmailIsExisted(GoogleLoginDTO googleLoginDTO) {
        String emailFromGoogleLogin = googleLoginDTO.getEmail();
        User userFromLoginByGoogle = userRepository.findUserByEmail(emailFromGoogleLogin).get();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userFromLoginByGoogle.getUsername(),
                        Const.passwordForGoogleLoginAndRegister)
        );
        TokenDTO token = tokenService.generateJwt(authentication) ;
        User trackedUser = userRepository.findUserByUsername(userFromLoginByGoogle.getUsername()).isPresent()
                ? userRepository.findUserByUsername(userFromLoginByGoogle.getUsername()).get()
                : null ;
        return new LoginResponseDTO(trackedUser,token);
    }

    private User handleGoogleLoginWhenEmailIsNOTExisted(GoogleLoginDTO googleLoginDTO) {
        String usernameGeneratedByUUID = UUID.randomUUID().toString();
        String idGeneratedByUUID = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(Const.passwordForGoogleLoginAndRegister);
        Role userRole = roleRepository.findByAuthority("USER").isPresent()
                ? roleRepository.findByAuthority("USER").get()
                : addIntoRoleRepositoryAndReturnDefaultUserRole();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        User newUserCreatedFromGoogleAccount = new User(idGeneratedByUUID,
                usernameGeneratedByUUID,encodedPassword,googleLoginDTO.getName(),googleLoginDTO.getPicture().toString(),
                googleLoginDTO.getEmail(),roles
        );
        userRepository.save(newUserCreatedFromGoogleAccount);
        return newUserCreatedFromGoogleAccount;
    }

    public LoginResponseDTO handleLoginAndRegisterWithGoogleAccount(GoogleLoginDTO googleLoginDTO) {
        String emailFromGoogleLogin = googleLoginDTO.getEmail();
        if(userRepository.findUserByEmail(emailFromGoogleLogin).isPresent()) {
            return handleGoogleLoginWhenEmailIsExisted(googleLoginDTO);
        }
        User newUserCreatedFromGoogleAccount = handleGoogleLoginWhenEmailIsNOTExisted(googleLoginDTO);
        return loginResponseDTO(newUserCreatedFromGoogleAccount.getUsername(),Const.passwordForGoogleLoginAndRegister);
    }

    public RegisterServiceReturnDTO registerUser(String username, String password, String email, String phoneNumber) {
        String message = "";
        Role userRole = roleRepository.findByAuthority("USER").isPresent()
                ? roleRepository.findByAuthority("USER").get()
                : addIntoRoleRepositoryAndReturnDefaultUserRole();
        String encodedPassword = passwordEncoder.encode(password);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        String encodedUserId = UUID.randomUUID().toString();
        User newApplicationUser = new User(encodedUserId,
                username, encodedPassword, email,phoneNumber ,roles,password
        );
        if(userRepository.findUserByEmail(email).isPresent()) {
            message += "Email is existed. ";
        }
        if(userRepository.findUserByPhoneNumber(phoneNumber).isPresent()) {
            message += "Phone number is existed. ";
        }
        if(userRepository.findUserByUsername(username).isPresent()) {
            message += "Username is existed. ";
        }
        if(message.isEmpty()) userRepository.save(newApplicationUser);
        return message.isEmpty()
                ? new RegisterServiceReturnDTO(newApplicationUser,"Register Success!")
                : new RegisterServiceReturnDTO(null,message);
    }

    public Boolean isRegisterAccepted(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    public User setUpLoginUser(String username,String password) {
        if(userRepository.findUserByUsername(username).isEmpty()) return null ;
        User responseApplicationUser = userRepository.findUserByUsername(username).get();
        if(!responseApplicationUser.getPassword().equals(passwordEncoder.encode(password))) return null ;
        return responseApplicationUser ;
    }

    public LoginResponseDTO loginResponseDTO(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            TokenDTO token = tokenService.generateJwt(authentication) ;
            User trackedUser = userRepository.findUserByUsername(username).isPresent()
                    ? userRepository.findUserByUsername(username).get()
                    : null ;
            return new LoginResponseDTO(trackedUser,token);
        } catch (Exception authenticationException) {
            System.out.println(authenticationException.getMessage());
            return new LoginResponseDTO(null,null);
        }
    }

    public User getDataFromUserNameService(String username) {
        return userRepository.findUserByUsername(username).isPresent() ? userRepository.findUserByUsername(username).get() : null;
    }

    public void configDb() {
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> {
            if(user.getDecodePassword() == null) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                String decodePassword = user.getPassword();
                user.setPassword(encodedPassword);
                user.setDecodePassword(decodePassword);
                Role userRole = roleRepository.findByAuthority("USER").isPresent()
                        ? roleRepository.findByAuthority("USER").get()
                        : addIntoRoleRepositoryAndReturnDefaultUserRole();
                Set<Role> roles = new HashSet<>();
                roles.add(userRole);
                user.setAuthorities(roles);
                userRepository.save(user);
            }
        });
        String[] stringArray = new String[] { "ADMIN", "SELLER" };
        List<String> listRole = Arrays.asList(stringArray);
        listRole.forEach(role -> {
            String fakeID = UUID.randomUUID().toString();
            Role newRole = new Role(fakeID,role);
            roleRepository.save(newRole);
        });
    }
}
