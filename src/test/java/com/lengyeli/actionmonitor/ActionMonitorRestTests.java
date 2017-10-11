package com.lengyeli.actionmonitor;

import com.lengyeli.actionmonitor.api.SampleDataService;
import com.lengyeli.actionmonitor.model.SampleData;
import com.lengyeli.actionmonitor.repository.SampleDataRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActionMonitorApplication.class)
@WebAppConfiguration
//@PropertySource("classpath:test.properties")
public class ActionMonitorRestTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${application.version}")
    private String appVersion;

    @Autowired
    private SampleDataRepository sampleDataRepository;

//    @MockBean
//    private SampleDataService sampleDataService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Testing the application status
     */
    @Test
    public void testStatus() throws Exception {
        this.mockMvc.perform(get("/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    /**
     * Testing the application version
     */
    @Test
    public void testGetVersion() throws Exception {
        mockMvc.perform(get("/version"))
                .andExpect(status().isOk())
                .andExpect(content().string(appVersion));
    }

    /**
     * Test for creating new {{@link SampleData}}
     */
    @Test
    public void testInsert() throws Exception {
        SampleData sampleData = new SampleData("Almafa");
        sampleData.setId(1L);

//        when(sampleDataService.createSampleData(sampleData.getText())).thenReturn(sampleData);

        mockMvc.perform(get("/insert/{text}", sampleData.getText()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andExpect(jsonPath("id").value(Matchers.notNullValue()))
                .andExpect(jsonPath("text").value(sampleData.getText()));
        sampleDataRepository.delete(sampleData);
    }

    /**
     * Test for updating {{@link SampleData}}
     */
    @Test
    public void testUpdateExistedData() throws Exception {
        SampleData sampleData = new SampleData("Arya Stark");
        sampleData.setId(1L);
        sampleDataRepository.save(sampleData);

        mockMvc.perform(
                get("/update/{id}/{text}", sampleData.getId(), "asd"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
        sampleDataRepository.delete(sampleData);
    }

    /**
     * Test for updating {{@link SampleData}}
     */
    @Test
    public void testUpdateNonExistedData() throws Exception {
        SampleData sampleData = new SampleData("Arya Stark");
        sampleData.setId(100L);

//        when(sampleDataService.findById(sampleData.getId())).thenReturn(null);
//        doNothing().when(sampleDataService).update(sampleData.getId(), sampleData.getText());

        mockMvc.perform(
                get("/update/{id}/{text}", sampleData.getId(), "asd"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test for creating new {{@link SampleData}}, that existed
     */
    @Test
    public void testDeleteExistedData() throws Exception {
        SampleData sampleData = new SampleData("Arya Stark");
        sampleData.setId(2L);
        sampleDataRepository.save(sampleData);
//        when(sampleDataService.findById(sampleData.getId())).thenReturn(sampleData);
//        doNothing().when(sampleDataService).delete(sampleData.getId());

        mockMvc.perform(
                get("/delete/{id}", sampleData.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    /**
     * Test for creating new {{@link SampleData}}, that non existed
     */
    @Test
    public void testDeleteNonExistedData() throws Exception {
        SampleData sampleData = new SampleData("Arya Stark");
        sampleData.setId(1L);
//        sampleDataRepository.save(sampleData);

//        when(sampleDataService.findById(sampleData.getId())).thenReturn(null);
//        doNothing().when(sampleDataService).delete(sampleData.getId());

        mockMvc.perform(
                get("/delete/{id}", sampleData.getId()))
                .andExpect(status().isNotFound());
    }
}
