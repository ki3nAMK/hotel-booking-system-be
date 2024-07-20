package com.loco.demo.Controller.Authen;

import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.AuthenDTO.*;
import com.loco.demo.DTO.JSON.*;
import com.loco.demo.DTO.Status.StatusAuthenticationLogin;
import com.loco.demo.DTO.Status.StatusAuthenticationRegister;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import com.loco.demo.services.EmailService.EmailService;
import com.loco.demo.services.authenService.AuthenticationService;
import com.loco.demo.utils.Converters.SecureUser;
import com.loco.demo.utils.Converters.UserMinimalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("api/v1/authen")
@CrossOrigin
public class AuthenticationController {
    private final EmailService emailService ;
    private final AuthenticationService authenticationService ;

    @Autowired
    public AuthenticationController(EmailService emailService, AuthenticationService authenticationService) {
        this.emailService = emailService;
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/register/testing")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUserTesting(body.getUsername(), body.getPassword());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public SchemaResponseStatusAuthenticationRegister registerUser(
            @RequestBody RegisterForm registerForm) {
        RegisterServiceReturnDTO response = authenticationService
                .registerUser(registerForm.username(), registerForm.password(), registerForm.email(), registerForm.phoneNumber());
        User applicationUser = response.user();
        String message = response.message();
        if(applicationUser == null) {
            return new SchemaResponseStatusAuthenticationRegister(message,"000"
                    , StatusAuthenticationRegister.IS_EXIST,null);
        }
        return new SchemaResponseStatusAuthenticationRegister(message,"001"
                ,StatusAuthenticationRegister.ACCEPTED,applicationUser) ;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST,value = "/login/test")
    public SchemaResponseStatusAuthenticationLogin loginUser(
            @RequestBody RegistrationDTO registrationDTO) {
        User applicationUser = authenticationService
                .setUpLoginUser(registrationDTO.getUsername(),registrationDTO.getPassword())!=null
                ? authenticationService.setUpLoginUser(registrationDTO.getUsername(),registrationDTO.getPassword())
                : null ;
        StatusAuthenticationLogin status = applicationUser!=null
                ? StatusAuthenticationLogin.SUCCESS
                : StatusAuthenticationLogin.NOT_FOUND ;
        return new SchemaResponseStatusAuthenticationLogin(status,applicationUser);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public LoginResponseDTO setUpLoginResponseDTO(
            @RequestBody RegistrationDTO registrationDTO) {
        return authenticationService.loginResponseDTO(
                registrationDTO.getUsername(),registrationDTO.getPassword());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET,value = "/client/getMe")
    public GetMeDataDTO getMeDataDTO() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = authentication.getName();
            User getUserFromDB = authenticationService.getDataFromUserNameService(usernameFromToken);
            if (getUserFromDB == null) {
                return new GetMeDataDTO(StatusResponseAPI.NOT_EXIST,"Username fetched from token is not exist","000",null);
            } else {
                return new GetMeDataDTO(StatusResponseAPI.ACCEPT,"Success !","001",getUserFromDB);
            }
        } catch (Exception exception) {
            String messageError = exception.getMessage();
            return new GetMeDataDTO(StatusResponseAPI.FAILED,messageError,"000",null);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST,value = "/client/sendEmail")
    public ResponseSendMail emailSender(@RequestParam(value = "file", required = false) MultipartFile[] file,
                                        String to, String[] cc, String subject, String body) {
        String errMessage = emailService.sendMail(file, to, cc, subject, body);
        boolean isErr = !errMessage.isEmpty();
        if(!isErr) {
            return new ResponseSendMail(StatusResponseAPI.OK,"001","Email send successful!",errMessage);
        } else {
            return new ResponseSendMail(StatusResponseAPI.FAILED,"000","Email send unsuccessful!",errMessage);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST,value = "/client/googleLogin")
    public AdvanceLoginResponseDTO googleLogin(@RequestBody GoogleLoginDTO googleLoginDTO) {
        LoginResponseDTO loginResponseDTO = authenticationService.handleLoginAndRegisterWithGoogleAccount(googleLoginDTO);
        SecureUser userGetFromGoogleLoginService = loginResponseDTO.getUser();
        UserMinimalConverter userMinimalConverter = new UserMinimalConverter(
                userGetFromGoogleLoginService.getEmail(),
                userGetFromGoogleLoginService.getName(),
                userGetFromGoogleLoginService.getName(),
                userGetFromGoogleLoginService.getName(),
                userGetFromGoogleLoginService.getAvatar(),
                (Set<Role>) userGetFromGoogleLoginService.getAuthorities()
        );
        return new AdvanceLoginResponseDTO(
                "Successful !","001",StatusAuthenticationRegister.ACCEPTED,
                userMinimalConverter,loginResponseDTO.getJwt()
        );
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET,value = "/config")
    public void config() {
        authenticationService.configDb();
    }
}
