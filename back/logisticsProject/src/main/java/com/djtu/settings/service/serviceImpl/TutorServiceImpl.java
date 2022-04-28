package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.RegisterException;
import com.djtu.settings.dao.TutorDao;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorDao tutorDao;

    @Override
    public void registerTutorUserNameVerify(String username) throws RegisterException {
        Tutor tutor = tutorDao.getTutorByUsername(username);
        if (tutor != null) {
            throw new RegisterException("用户名已被注册");
        }
    }

    @Override
    public Tutor getTutorByUsername(String username) {
        return tutorDao.getTutorByUsername(username);
    }

    @Override
    public String getTutorSaltById(String id) {
        return tutorDao.getTutorSaltById(id);
    }

    @Override
    public synchronized int editTutor(Tutor tutor) {
        Tutor oldTutor = tutorDao.getTutorById(tutor.getId());

        int usernameCount = tutorDao.getUsernameCountByUsername(tutor.getUsername());

        if (oldTutor.getUsername().equals(tutor.getUsername()) || usernameCount == 0) {
            return tutorDao.editTutor(tutor);
        }
        return 0;
    }

}
