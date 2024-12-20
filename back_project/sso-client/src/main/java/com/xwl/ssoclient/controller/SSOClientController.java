package com.xwl.ssoclient.controller;

import com.xwl.model.ClientResponse;
import com.xwl.model.User;
import com.xwl.model.UserInfoResp;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Value("${sso-server.url}")
    private String url;

    @PostMapping("/msg")
    public ClientResponse getClientMsg(HttpServletResponse response, @RequestParam String token, HttpSession session) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setCode(1000);
        if (session.getAttribute(token) != null) {
            // 用户已登录且信息已存入session
            clientResponse.setUsername((String) session.getAttribute(token));
            return clientResponse;
        }
        // 去SSO服务端查询是否登录（token是否有效）
        if (!StringUtil.isNullOrEmpty(token)) {
            WebClient webClient = WebClient.create();
            Mono<UserInfoResp> userMono = webClient.get()
                    .uri(url + "?token=" + token)
                    .retrieve()
                    .bodyToMono(UserInfoResp.class);
            UserInfoResp user = userMono.block();
            if (null != user && !StringUtil.isNullOrEmpty((String) user.getData())) {
                // todo 类型转换异常
                clientResponse.setUsername((String) user.getData());
                // 成功登录则存入session
                session.setAttribute(token, user.getData());
                return clientResponse;
            }
        }
        // 未登录
        try {
            response.sendRedirect("http://localhost:9980/login");
            clientResponse.setCode(999);
            return clientResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
