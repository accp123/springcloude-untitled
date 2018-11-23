package com.example.demosso.service;
import com.example.demosso.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@Service
public class SsoService {
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "checkUserFallbackMethod")//进行容错处理
    public User checkUser(String username, String password) {
        String serviceId = "pan-user";
        //打印一下到底调用的是哪个
        ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() +
                ":" + serviceInstance.getPort());

        User user= this.restTemplate.getForObject("http://" + serviceId + "/user/" + username,User.class);
        if(user!=null){
            String pwd = "123";
            if(pwd.equals(password)){
                return user;
            }
        }
        return null;
    }
    public User checkUserFallbackMethod(String username,String password){ // 请求失败执行的方法
        return new User(null, "获取用户信息出错!", null, null, null,null,null,null,null);
    }
}
