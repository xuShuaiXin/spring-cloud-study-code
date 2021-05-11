package com.xsx.cloudalibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xsx.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;



//Sentinel配置异常兜底方法类
public class CustomerBlockHandler {
    /**
     *  注意事项:
     *      1.兜底方法必须在原参数之后增加一个 BlockException参数
     *      2.兜底方法必须是 public static修饰的
     */
    public static CommonResult BlockHandler1(BlockException exception){
        return new CommonResult(444,"处理失败 in CustomerBlockHandler 2");
    }
    public static CommonResult BlockHandler2(BlockException blockException){
        return new CommonResult(444,"处理失败 in CustomerBlockHandler 2");
    }
}
