package com.djtu.settings.service;


import com.djtu.settings.vo.UserVo;

public interface StudentService {
    UserVo getUserVoByUsername(String username);
}
