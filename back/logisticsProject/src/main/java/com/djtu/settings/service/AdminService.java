package com.djtu.settings.service;

import com.djtu.settings.vo.UserVo;

public interface AdminService {
    UserVo getUserVoByUsername(String username);
}
