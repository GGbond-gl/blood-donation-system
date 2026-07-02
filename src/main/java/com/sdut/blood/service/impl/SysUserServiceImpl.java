package com.sdut.blood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.blood.common.exception.BusinessException;
import com.sdut.blood.common.utils.JwtUtil;
import com.sdut.blood.domain.dto.LoginDTO;
import com.sdut.blood.domain.entity.SysUser;
import com.sdut.blood.domain.vo.LoginVO;
import com.sdut.blood.mapper.SysUserMapper;
import com.sdut.blood.service.SysUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginVO login(LoginDTO dto) {
        // 1. 查询用户
        SysUser user = getByUsername(dto.getUsername());
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }

        // 2. 校验账号状态
        if (user.getStatus() == 1) {
            throw new BusinessException("账号已被禁用，请联系管理员");
        }

        // 3. 校验密码
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 4. 生成JWT令牌
        String token = JwtUtil.generateToken(user.getId(), user.getRole());

        // 5. 封装返回结果
        LoginVO vo = new LoginVO();
        vo.setToken(token);
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setRealName(user.getRealName());
        vo.setRole(user.getRole());
        return vo;
    }

    @Override
    public SysUser getByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }
}