package com.sdut.blood.filter;

import com.sdut.blood.common.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * JWT令牌认证过滤器
 * 每个请求执行一次，校验请求头中的Bearer令牌
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. 从请求头获取Authorization字段
        String authHeader = request.getHeader("Authorization");

        // 2. 无令牌直接放行，交由Security后续处理未认证逻辑
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 提取令牌内容（去掉Bearer前缀）
        String token = authHeader.substring(7);

        try {
            // 4. 校验令牌有效性
            if (!JwtUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 5. 解析用户ID与角色
            Long userId = JwtUtil.getUserIdFromToken(token);
            String role = JwtUtil.getRoleFromToken(token);

            // 6. 封装认证信息，存入安全上下文
            // principal存储用户ID，权限集合存储角色
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            Collections.singletonList(authority)
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            // 令牌解析异常，直接放行，不中断请求
            logger.warn("JWT令牌解析异常：" + e.getMessage());
        }

        // 7. 继续执行后续过滤器
        filterChain.doFilter(request, response);
    }
}