package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.settings.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Set<String> getPermissionByUsername(String username) {
        HashSet<String> strings = new HashSet<>();
        strings.add("course:choose");
        strings.add("course:history");
        strings.add("grade:this");
        strings.add("course:drop");
        strings.add("course:result");
        strings.add("grade:history");
        return strings;
    }

}
