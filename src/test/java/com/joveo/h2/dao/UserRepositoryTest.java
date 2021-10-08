package com.joveo.h2.dao;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@ContextConfiguration(classes = {UserRepository.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;

    @BeforeAll
    public void setup(){
        User user = new User();
        user.setName("Santosh");
        JSONObject address = new JSONObject();
        address.put("Pin code",584125);
        address.put("City","Mudgal");
        address.put("Address-1","RK nagar house no 182");
        user.setAddress(address);

        userRepository.save(user);
    }

    @Test
    public void testFindByUserName(){
        ArrayList<User> users = userRepository.findByUserName("Santosh");
        Assertions.assertTrue(users.size()!=0);
        Assertions.assertTrue(users.get(0).getName().compareTo("Santosh")==0);
    }
}
