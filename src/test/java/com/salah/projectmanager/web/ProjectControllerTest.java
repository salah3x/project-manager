package com.salah.projectmanager.web;

import com.salah.projectmanager.service.AdminService;
import com.salah.projectmanager.service.CollaboratorService;
import com.salah.projectmanager.service.ManagerService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by salah on 7/18/17.
 */
public class ProjectControllerTest {

    @Mock
    private ManagerService managerService;

    @Mock
    private CollaboratorService collaboratorService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).setViewResolvers(viewResolver).build();
    }
}
