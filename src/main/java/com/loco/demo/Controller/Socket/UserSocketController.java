//package com.loco.demo.Controller.Socket;
//
//import com.loco.demo.AuthenModel.User;
//import com.loco.demo.DTO.JSON.IListResponse;
//import com.loco.demo.DTO.JSON.ListResponse;
//import com.loco.demo.DTO.JSON.Socket.ConnectUserRequest;
//import com.loco.demo.services.userService.UserService;
//import com.loco.demo.services.userService.UserServiceImpl;
//import com.loco.demo.utils.Converters.SecureUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//@CrossOrigin
//public class UserSocketController {
//    private final UserServiceImpl userService;
//
//    @Autowired
//    public UserSocketController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @MessageMapping("/user.addUser")
//    @SendTo("/api/v1/ws/user/public")
//    public User addUser(
//            @Payload ConnectUserRequest user
//            ) {
//        return userService.connect(user.id());
//    }
//
//    @MessageMapping("/user.disconnectUser")
//    @SendTo("/api/v1/ws/user/public")
//    public User disconnectUser(
//            @Payload String userId
//    ) {
//        return userService.disconnect(userId);
//    }
//
//    @GetMapping("/api/v1/ws/users")
//    public ResponseEntity<List<SecureUser>> findConnectedUsers() {
//        List<SecureUser> users = userService.findOnlineUser(true).stream().map(SecureUser::new).toList();
//        return ResponseEntity.ok(users);
//    }
//}
