package com.xwl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ruoling
 * @date 2024/12/4 17:25:47
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private String username;
    private String msg;
    private int code;
}
