package com.zylliondata.d4i.projectname;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApi3 {

    @Bean
    public OpenAPI springShopOpenAPI() {

        Server local = new Server();
        local.setUrl("http://127.0.0.1:5273");
        local.setDescription("本地");

        Server cluster = new Server();
        cluster.setUrl("http://PROJECT_NAME.edge.zylliondata.local");
        cluster.setDescription("研发集群");

        List<Server> servers = new ArrayList<>();
        servers.add(local);
        servers.add(cluster);

        return new OpenAPI()
                .info(
                        new Info()
                                .title("PROJECT_NAME")
                                .description("description")
                                .version("0.3.0")
                                .license(
                                        new License()
                                                .name("© 版权所有 2016 - " + LocalDateTime.now().getYear() + " 中云开源数据技术（上海）有限公司保留一切权利")
                                                .url("http://www.zylliondata.com/site/aboutUs")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("GitLab 项目地址")
                                .url("http://gitlab.zylliondata.local/....")
                )
                .servers(servers)
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearer-auth",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )

                );
    }
}
