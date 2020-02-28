package com.shrinkster.userservice;

import com.shrinkster.userservice.constants.Roles;
import com.shrinkster.userservice.exception.DatabaseEmptyException;
import com.shrinkster.userservice.model.User;
import com.shrinkster.userservice.repository.IUserRepository;
import com.shrinkster.userservice.service.IGoogleService;
import com.shrinkster.userservice.service.IUserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = UserServiceApplication.class)
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private static User user;

    @Autowired
    private IGoogleService iGoogleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository iUserRepository;

    @BeforeAll
    public static void userSetup() {
        user = new User("dummy","dummy@gmail.com", "https://googlepics", false);
        user.setRoles(Roles.USER);
    }
    @Test
    @Order(1)
    public void addUser() {
        iGoogleService.addUserData(user);
    }

    @Test
    @Order(2)
    public void avoidDuplicate() {
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            iGoogleService.addUserData(user);
        });
    }

    @Test
    @Order(3)
    public void getUserProfileDetails() {
        List<User> users = userService.getUsers();
        for (User list: users) {
            if (list.getEmail().equals("dummy@gmail.com")){
                user = list;
            }
        }
        assertEquals("dummy", user.getName());
        assertNotNull(user.getName());
        assertEquals("dummy@gmail.com", user.getEmail());
        assertNotNull(user.getEmail());
        assertEquals("https://googlepics", user.getAvatarURL());
        assertNotNull(user.getAvatarURL());
        assertEquals(Roles.USER, user.getRoles());
        assertNotNull(user.getRoles());
    }

    @Test
    @Order(4)
    public void exceptionEmpty() {
        iUserRepository.deleteAll();
        Assertions.assertThrows(DatabaseEmptyException.class, () -> {
            userService.findAllUsers();
        });
    }
}
