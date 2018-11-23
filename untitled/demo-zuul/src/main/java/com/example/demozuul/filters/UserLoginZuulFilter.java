package com.example.demozuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component //加入到Spring容器
public class UserLoginZuulFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true; // 该过滤器需要执行
    }

    @Override
    public Object run() { //编写业务逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println(request.getMethod()+"\t"+ request.getRequestURL().toString());

        String token = request.getParameter("token");// 获取请求的参数

        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;
        }

    }

    @Override
    public String filterType() {
        return "pre"; // 设置过滤器类型为：pre
    }

    @Override
    public int filterOrder() {
        return 0;// 设置执行顺序
    }

}
