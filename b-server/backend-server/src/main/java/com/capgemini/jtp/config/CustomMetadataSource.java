package com.capgemini.jtp.config;

import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;


//每次访问都要调用，进行访问拦截，根据你的路径来查询角色

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
//路径匹配符

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    //这个方法就是为了获取角色

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/login_p".equals(requestUrl)) {
            return null;
        }
        List<Menu> allMenu = menuService.getAllMenu();
        for (Menu menu : allMenu) {
            //遍历所有的menu，进行匹配请求路径和数据库路径匹配

            if (antPathMatcher.match(menu.getUrl(), requestUrl)
                    &&menu.getRoles().size()>0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                //匹配资源后，传入角色

                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
