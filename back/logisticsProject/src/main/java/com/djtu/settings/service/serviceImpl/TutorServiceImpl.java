package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.TutorDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.service.TutorService;
import com.djtu.settings.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorDao tutorDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserVo getUserVoByUsername(String username) {
        Tutor tutor = tutorDao.getTutorByUsername(username);
        String userId = userDao.getUserIdByTutorId(tutor.getId());
        if (tutor == null) {
            return null;
        }
        return new UserVo(userId, tutor.getUsername(), tutor.getPassword(), tutor.getSalt(), tutor.getName(),
                tutor.getSex(), tutor.getRemark(), tutor.getAvatarPath());
    }

    @Override
    public String getUserIdByTutorId(String id) {
        return userDao.getUserIdByTutorId(id);
    }

}
