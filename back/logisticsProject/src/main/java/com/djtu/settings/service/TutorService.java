package com.djtu.settings.service;

import com.djtu.settings.vo.UserVo;

public interface TutorService {
    UserVo getUserVoByUsername(String username);
}
