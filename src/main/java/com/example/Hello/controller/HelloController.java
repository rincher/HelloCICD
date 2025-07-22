package com.example.Hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<?> returnHello(){
        return ResponseEntity.ok("Hello You");
    }

   @GetMapping("/")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok("Cake is a like");
    }

    @GetMapping("/help")
    public ResponseEntity<?> askHelp(){
        return ResponseEntity.ok("Look around");
    }

    @GetMapping("/unstuck")
    public ResponseEntity<?> teleport(){
        return ResponseEntity.ok("Look what you made me do?!");
    }
}

