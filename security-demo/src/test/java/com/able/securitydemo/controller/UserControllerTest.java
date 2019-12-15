package com.able.securitydemo.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Resource
    private WebApplicationContext webApplicationContext;
    @Resource
    private MockMvc mockMvc;

//    @Before
//    public void init() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void test() throws Exception {
        final String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "卡卡西")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void testQueryDetial() throws Exception {
        final val contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void testWhenParamError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    public void testWhenCreateSuccess() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("username","旗木卡卡西")
                        //.param("password","123456")
                        )
                .andReturn().getResponse();
        log.info("status={}",response.getStatus());
        log.info("content={}",response.getContentAsString());

    }

    @Test
    public void testWhenUpdateSuccess() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("username","admin")
                .param("password","12345")
        )
                .andReturn().getResponse();
        log.info("status={}",response.getStatus());
        log.info("content={}",response.getContentAsString());

    }

}
