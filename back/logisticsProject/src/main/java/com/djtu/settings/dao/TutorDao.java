package com.djtu.settings.dao;

import com.djtu.settings.pojo.Tutor;

public interface TutorDao {
    Tutor getTutorByUsername(String username);
}
