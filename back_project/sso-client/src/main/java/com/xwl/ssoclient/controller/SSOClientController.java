package com.xwl.ssoclient.controller;

import com.xwl.model.ClientResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ruoling
 * @date 2024/12/4 17:24:47
 * @description
 */
@RestController
@RequestMapping("/client")
@CrossOrigin(exposedHeaders = "location")
public class SSOClientController {

    @PostMapping("/msg")
    public ClientResponse getClientMsg(HttpServletResponse response, @RequestParam String username) {
        if (username == null || username.equals("") || "null".equals(username)) {
            try {
                response.sendRedirect("http://localhost:9980/login?source=edk");
                ClientResponse clientResponse = new ClientResponse();
                clientResponse.setCode("999");
                return clientResponse;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setUsername(username);
        clientResponse.setCode("1000");
        return clientResponse;
    }
}
