/*TODO:
    unsubscribe
    user's servers ( list<server> )
* */

package com.gato.demo.controller;

import com.gato.demo.model.AddUserServerDTO;
import com.gato.demo.model.Server;
import com.gato.demo.model.User;
import com.gato.demo.repository.ServerRepository;
import com.gato.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AppController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ServerRepository serverRepository;
    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/addServer")
    public ResponseEntity addServer(@RequestBody Server server){
        logger.info("Name:{} Desc:{} Status:{}", server.getName(), server.getDesc(), server.getStatus());
        serverRepository.save(server);
        return ResponseEntity.ok(server);
    }

    @GetMapping(value = "/getAllServers")
    public ResponseEntity getAllServers(){
        List<Server> servers = serverRepository.findAll();
        return ResponseEntity.ok(servers);
    }

    // User

//    @PostMapping(value = "/registerUser")
//    public ResponseEntity registerUser(@RequestBody User user){
//        logger.info("Name:{} Password:{} Email:{}", user.getUsername(), user.getPassword(), user.getEmail());
//        if (userRepository.findByUsername(user.getUsername()).size()!=0 || userRepository.findByEmail(user.getEmail()).size()!=0){
//            return ResponseEntity.ok("EMAIL or USERNAME not valid");
//        }
//        else {
//            userRepository.save(user);
//            return ResponseEntity.ok(user);
//        }
//    }
//
//    @PostMapping(value = "/loginUser")
//    public ResponseEntity loginUser(@RequestBody User user){
//        logger.info("Name:{} Password:{}", user.getUsername(), user.getPassword());
//        if (userRepository.findByUsername(user.getUsername()).size()!=0 &&
//                Objects.equals(userRepository.findByUsername(user.getUsername()).get(0).getPassword(), user.getPassword())){
//            return ResponseEntity.ok(user);
//        }
//        else {
//            return ResponseEntity.ok("PASSWORD or USERNAME not valid");
//        }
//    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping(value = "/getUsersByServer/{server}")
    public ResponseEntity getUsersByServer(@PathVariable String server){
        return ResponseEntity.ok(serverRepository.findByName(server).get(0).getUserList());
    }

    @GetMapping(value = "/getUserCountByServer/{server}")
    public ResponseEntity getUserCountByServer(@PathVariable Long server){
        return ResponseEntity.ok(serverRepository.findById(server).get().getUserList().size());
    }

    @GetMapping(value = "/getStatusByServer/{server}")
    public ResponseEntity getStatusByServer(@PathVariable Long server){
        return ResponseEntity.ok(serverRepository.findById(server).get().getStatus());
    }

    //
    @PostMapping(value = "/addUserToServer")
    public ResponseEntity addUser(@RequestBody AddUserServerDTO dto){
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Server> server=serverRepository.findById(dto.getServerId());
        user.get().getServerList().add(server.get());
        server.get().getUserList().add(user.get());
        userRepository.save(user.get());
        serverRepository.save(server.get());
        return ResponseEntity.ok(server.get());
    }

}
