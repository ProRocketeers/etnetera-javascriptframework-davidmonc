package com.etnetera.hr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Class used for Spring Boot/MVC based tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JavaScriptFrameworkIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    public void getFrameworks_shouldReturnEmpty() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").doesNotExist());
    }

    @Test
    @DirtiesContext
    public void addFramework() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("aaa", "1.1", "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // get all
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").exists());
    }

    @Test
    @DirtiesContext
    public void getFramework() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("aaa", "1.1", "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    @DirtiesContext
    public void updateFramework() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("aaa", "1.1", "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/frameworks/{id}", 1)
                        .content(asJsonString(createDto("bbb", "2.1", "2021-08-26", 66)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("bbb"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.version").value("2.1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deprecationDate").value("2021-08-26"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hypeLevel").value("66"));
    }

    @Test
    @DirtiesContext
    public void deleteFramework() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("aaa", "1.1", "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/frameworks/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/frameworks/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void addFramework_withoutMandatoryFields_shouldReturnBadRequest() throws Exception {
        // mandatory name
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto(null, "1.1", "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // mandatory version
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("abcd", null, "2019-08-26", 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DirtiesContext
    public void addFramework_withoutNotMandatoryFields_shouldReturnCreated() throws Exception {
        // not mandatory deprecationDate
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/frameworks")
                        .content(asJsonString(createDto("no-deprecation-date", "1.1", null, 99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Dto createDto(String name, String version, String deprecationDate, int hypeLevel) {
        Dto dto = new Dto();
        dto.setName(name);
        dto.setVersion(version);
        dto.setDeprecationDate(deprecationDate);
        dto.setHypeLevel(hypeLevel);

        return dto;
    }

    /**
     * Test dto class, holds also deprecationDate as a String.
     */
    class Dto {
        private String name;
        private String version;
        private String deprecationDate;
        private int hypeLevel;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDeprecationDate() {
            return deprecationDate;
        }

        public void setDeprecationDate(String deprecationDate) {
            this.deprecationDate = deprecationDate;
        }

        public int getHypeLevel() {
            return hypeLevel;
        }

        public void setHypeLevel(int hypeLevel) {
            this.hypeLevel = hypeLevel;
        }
    }
}
