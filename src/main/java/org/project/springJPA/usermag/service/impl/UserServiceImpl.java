package org.project.springJPA.usermag.service.impl;

import org.project.springJPA.beans.UserBean;
import org.project.springJPA.usermag.repository.UserRepository;
import org.project.springJPA.usermag.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepostory;
    @Override
    public void saveUserInfo(UserBean bean) {
        userRepostory.save(bean);
    }

    @Override
    public void updateUserInfo(UserBean bean) {
        userRepostory.saveAndFlush(bean);//更新数据
    }

    @Override
    public void deleteUserInfo(UserBean bean) {
        userRepostory.delete(bean);
    }

    @Override
    public UserBean getUserInfo(int id) {
        return userRepostory.getOne(id);
    }
}
