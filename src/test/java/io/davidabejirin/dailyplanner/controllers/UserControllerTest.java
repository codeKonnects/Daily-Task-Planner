package io.davidabejirin.dailyplanner.controllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.davidabejirin.dailyplanner.dto.UserDto;
import io.davidabejirin.dailyplanner.entity.User;
import io.davidabejirin.dailyplanner.repository.UserRepository;
import io.davidabejirin.dailyplanner.service.serviceImpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(classes = {UserController.class, UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserController#createAccount(UserDto)}
     */
    @Test
    void testCreateAccount() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setTasks(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user1);
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user", "userDto"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    /**
     * Method under test: {@link UserController#getLoginForm()}
     */
    @Test
    void testGetLoginForm() {
        ModelAndView actualLoginForm = (new UserController(new UserServiceImpl(mock(UserRepository.class))))
                .getLoginForm();
        assertTrue(actualLoginForm.isReference());
        assertSame(actualLoginForm.getModel(), actualLoginForm.getModelMap());
    }

    /**
     * Method under test: {@link UserController#login(String, String, User)}
     */
    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("email", "foo")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("email", "user"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    /**
     * Method under test: {@link UserController#login(String, String, User)}
     */
    @Test
    void testLogin2() throws Exception {
        User user = new User();
        user.setEmail("foo");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setTasks(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("email", "foo")
                .param("password", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("email", "user"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    /**
     * Method under test: {@link UserController#signup()}
     */
    @Test
    void testSignup() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/signup");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("register"));
    }

    /**
     * Method under test: {@link UserController#signup()}
     */
    @Test
    void testSignup2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/signup");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("register"));
    }
}

