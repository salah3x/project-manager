package com.salah.projectmanager.web;

import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.service.GuestService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by salah on 7/16/17.
 */
public class IndexControllerTest {

    @Mock
    private GuestService guestService;

    private MockMvc mockMvc;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        mockMvc = MockMvcBuilders.standaloneSetup(indexController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("title",equalTo("Project Manager")));
        verifyZeroInteractions(guestService);
    }
    @Test
    public void testSearch() throws Exception{
        List<Project> list = new ArrayList<>();
        list.add(new Project());
        list.add(new Project());

        when(guestService.search("pr")).thenReturn(list);

        mockMvc.perform(get("/search?p=pr"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attribute("keyword", equalTo("pr")))
                .andExpect(model().attribute("projects", hasSize(2)));
    }
    @Test
    public void testWiki() throws Exception{
        mockMvc.perform(get("/wiki"))
                .andExpect(status().isOk())
                .andExpect(view().name("wiki"))
                .andExpect(model().attribute("title", equalTo("Wiki")));
        verifyZeroInteractions(guestService);
    }
    @Test
    public void testMessage()throws Exception{
        mockMvc.perform(get("/message")
        .param("email","test@exemple.com")
        .param("msg","hello"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/?success"));
    }
    @Test
    public void testSignup()throws Exception{
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user",instanceOf(User.class)))
                .andExpect(model().attribute("user",hasProperty("id",is(0))))//Why id == 0 ?!!
                .andExpect(model().attribute("title","Sign up"))
                .andExpect(view().name("signup"));
    }
    @Test
    @Ignore//need to comment passworeencoder.encode() in IndexController
    public void testSignUp()throws Exception{
        when(guestService.signIn("salah")).thenReturn(null);

        mockMvc.perform(post("/signup")
        .param("username","salah")
        .param("password","123")
        .param("email", "test@exemple.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/signup?success"));

        ArgumentCaptor<User> user1 = ArgumentCaptor.forClass(User.class);
        verify(guestService).signUp(user1.capture());

        assertThat("salah", equalTo(user1.getValue().getUsername()));
        assertThat("123", equalTo(user1.getValue().getPassword()));
        assertThat("test@exemple.com", equalTo(user1.getValue().getEmail()));
        assertThat("img_avatar2.png", equalTo(user1.getValue().getAvatar()));
    }
    @Test
    public void testValidation()throws Exception{
        mockMvc.perform(post("/signup")
                .param("username","")
                .param("password","123")
                .param("email", "test@exemple.com"))//remove @
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attribute("error","error"))
                .andExpect(model().attribute("title","Sign up"));
        verifyZeroInteractions(guestService);
    }
    @Test
    public void testUsernameExist()throws Exception{
        when(guestService.signIn("salah")).thenReturn(new User());
        mockMvc.perform(post("/signup")
                .param("username","salah")
                .param("password","123")
                .param("email", "test@exemple.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attribute("double","Username already exist"));

        verify(guestService, times(1)).signIn("salah");
    }
}
