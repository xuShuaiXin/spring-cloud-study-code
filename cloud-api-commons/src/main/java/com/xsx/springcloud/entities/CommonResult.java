package com.xsx.springcloud.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CommonResult<T>
{
    private Integer code;
    private String  msg;
    private T       data;

    public CommonResult(Integer code,String message)
    {
        this(code,message,null);
    }
}
