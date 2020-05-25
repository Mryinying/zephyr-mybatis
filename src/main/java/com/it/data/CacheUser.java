package com.it.data;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 缓存用户信息
 * <br/>
 */
@Data
@Accessors(chain = true)
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String name;

    private Integer state;

    private String userName;

    private String token;
}
