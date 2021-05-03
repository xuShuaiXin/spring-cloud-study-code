package com.xsx.springcloud.entities;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString
public class Payment implements Serializable
{
    private Long id;
    private String serial;
}
