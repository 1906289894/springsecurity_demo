package com.zylliondata.d4i.projectname;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebAppConfiguration
@SpringBootTest(classes = {ProjectApplication.class})
@AutoConfigureMockMvc
class OpenApi2FileTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createYaml() throws Exception {
        MvcResult yamlResult = this.mockMvc.perform(get("/v3/api-docs.yaml")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = yamlResult.getResponse();
        String swaggerJson = response.getContentAsString();
        String outputDir = "target/openapi";
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "openapi.yaml"), StandardCharsets.UTF_8)) {
            writer.write(swaggerJson);
        }
    }
}