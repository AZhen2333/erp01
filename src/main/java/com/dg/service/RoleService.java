package com.dg.service;

import com.dg.pojo.Role;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

public interface RoleService {
    public Role selectById(Integer roleId);
}
