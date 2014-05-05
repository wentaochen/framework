//package com.weixin.controller;
//
//import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.server.setup.MockMvcBuilders.*;
//
//import org.junit.Test;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//public class WebContextConfigurationTest {
//
//    @Test
//    public void testWebFlow() throws Exception {
//        annotationConfigSetup(WebContextConfigurationTest.TestConfiguration.class)
//            .build()
//            .perform(post("/login").contentType(MediaType.APPLICATION_JSON).body("{\"username\":\"user\",\"password\":\"password\"}".getBytes()))
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"username\":\"user\",\"password\":\"password\"}"));
//    }
//
//    @Configuration
//    @EnableWebMvc
//    @ComponentScan(basePackages="com.weixin")
//    public static class TestConfiguration{
//
//    }
//}
//
//@Controller
//class JsonController{
//    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public Login login(@RequestBody Login login) {
//        System.out.println(login);
//        return login;
//    }
//}
//
//class Login{
//    private String username;
//    private String password;
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//}