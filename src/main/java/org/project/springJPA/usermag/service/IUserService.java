package org.project.springJPA.usermag.service;

import org.project.springJPA.beans.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {
    void saveUserInfo(UserBean bean);
    void updateUserInfo(UserBean bean);
    void deleteUserInfo(UserBean bean);
    UserBean getUserInfo(int id);
    public void deleteUserInfoList(List<UserBean> user);

    public UserBean getUserInfoById(Long id);

    public List<?> findAll();
    /**
     * 根据用户名与年龄查询用户
     * @param userName
     * @param age
     * @return
     */
    public void updateUserBean(String userName,Integer age,Long id);
    public List<?> findByUserNameStartingWithAndAge(String userName,Integer age);

    public List<?> findByUserNameAndAge(String userName,Integer age);

    public List<?> findByUserNameContainingAndAge(String userName,Integer age);

    public List<?> findUsersByParams(String userName,Integer age,String password);

    public Page<UserBean> findUsersByParamsToPage(String userName, Integer age, String password, Pageable pageable);

    public Page<UserBean> findUsersByUserBeanToPage(UserBean user,Pageable pageable);

    public Page<UserBean> findUsersByMapToPage(Map map, Pageable pageable);

    public List<UserBean> findUsersByMap(Map map);
}
