package com.djtu.permission.service.serviceImpl;

import com.djtu.permission.dao.RoleDao;
import com.djtu.permission.pojo.Role;
import com.djtu.permission.service.RoleService;
import com.djtu.settings.dao.StudentDao;
import com.djtu.permission.pojo.vo.StudentRoleVo;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.permission.pojo.vo.TutorRoleVo;
import com.djtu.settings.pojo.vo.TutorSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Role> getRoleListByUserId(String userId) {
        return roleDao.getRoleListByUserId(userId);
    }

    @Override
    public int addUserRole(String userId, Integer roleId, String id) {
        return roleDao.addUserRole(userId, roleId, id);
    }

    @Override
    public int removeUserRole(String userId, Integer roleId) {
        return roleDao.removeUserRole(userId, roleId);
    }

    @Override
    public Set<String> getRolesByUserId(String userId) {
        return roleDao.getRoleSetByUserId(userId);
    }

    @Override
    public List<StudentRoleVo> getStudentUserRoleVoList(StudentSearchVo studentSearchVo, int pageCount, int pageSize) {
        return roleDao.getStudentUserRoleVoList(studentSearchVo, pageCount, pageSize);
    }

    @Override
    public List<TutorRoleVo> getTutorUserRoleVoList(TutorSearchVo tutorSearchVo, Integer pageNo, Integer pageSize) {
        return roleDao.getTutorUserRoleVoList(tutorSearchVo, pageNo, pageSize);
    }

    @Override
    public Integer getStudentRoleListTotal(StudentSearchVo studentSearchVo) {
        return roleDao.getStudentUserRoleVoListTotal(studentSearchVo);
    }

    @Override
    public Integer getStudentRoleListTotal(String tutorId, StudentSearchVo studentSearchVo) {
        return studentDao.getStudentByTutorTotal(tutorId, studentSearchVo);
    }

    @Override
    public Integer getTutorRoleListTotal(TutorSearchVo tutorSearchVo) {
        return roleDao.getTutorUserRoleVoListTotal(tutorSearchVo);
    }

}
