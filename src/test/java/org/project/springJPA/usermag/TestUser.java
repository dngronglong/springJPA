package org.project.springJPA.usermag;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.springJPA.beans.UserBean;
import org.project.springJPA.usermag.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {
    @Resource
    private IUserService userServiceImpl;
    @Resource
    private LocalContainerEntityManagerFactoryBean emf;
    @Test
    public void addUser(){
        UserBean user=new UserBean();
        user.setName("小明");
        user.setPwd("123");
        user.setSex("女");
        userServiceImpl.saveUserInfo(user);
    }
    @Test
    public void findUsersByMap() {
        Map params = new HashMap<>();
        params.put("age", 19);
        params.put("userName", "张");
        params.put("password", "123456");

        List<UserBean> users = userServiceImpl.findUsersByMap(params);

        System.out.println(users);

    }

    @Test
    public void findUsersByMapToPage() {
        Map params = new HashMap<>();
        params.put("age", 19);
        params.put("userName", "张");
        params.put("password", "123456");

        //在spring data jpa中分页从0开始，0代表第1页的数据
        //new Sort(Direction.ASC, "id","birthday") 代表要针对某些属性，进行升序或者降序
        Pageable pageable = new PageRequest(0, 10,new Sort(Sort.Direction.ASC, "id","birthday"));

        //针对用户完成分页
        Page<UserBean> page = userServiceImpl.findUsersByMapToPage(params, pageable);


        System.out.println(page.hasPrevious());//是否有上一页
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.getNumber());//当前页码
        System.out.println(page.getTotalElements());//总共有多少条
        System.out.println(page.getContent());//具体的数据
        System.out.println(page.getSize());//每页显示的行数
        System.out.println(page.getTotalPages());//总页数
    }






//    @Test
//    public void findUsersByUserBeanToPage() {
//        UserBean user = new UserBean();
//        user.setAge(19);
//        user.setUserName("张");
//        user.setPassword("123456");
//
//        //在spring data jpa中分页从0开始，0代表第1页的数据
//        //new Sort(Direction.ASC, "id","birthday") 代表要针对某些属性，进行升序或者降序
//        Pageable pageable = new PageRequest(0, 10,new Sort(Sort.Direction.ASC, "id","birthday"));
//
//        //针对用户完成分页
//        Page<UserBean> page = userServiceImpl.findUsersByUserBeanToPage(user, pageable);
//
//
//        System.out.println(page.hasPrevious());//是否有上一页
//        System.out.println(page.hasNext());//是否有下一页
//        System.out.println(page.getNumber());//当前页码
//        System.out.println(page.getTotalElements());//总共有多少条
//        System.out.println(page.getContent());//具体的数据
//        System.out.println(page.getSize());//每页显示的行数
//        System.out.println(page.getTotalPages());//总页数
//    }





//    @Test
//    public void updateUserBean() {
//        List<?> datas = userServiceImpl.findByUserNameContainingAndAge("张", 19);
//        Assert.assertNotEquals(0, datas.size());
//
//        UserBean user = (UserBean) datas.get(0);
//        user.setPwd("25");
//        user.setName("狗蛋");
//
//        userServiceImpl.updateUserBean(user.getName(), user.getPwd(), user.getId());
//
//    }


    @Test
    public void findUsersByParamsToPage() {
        String userName = "张";
        Integer age = 19;
        String password = "123456";


        //在spring data jpa中分页从0开始，0代表第1页的数据
        //new Sort(Direction.ASC, "id","birthday") 代表要针对某些属性，进行升序或者降序
        Pageable pageable = new PageRequest(0, 10,new Sort(Sort.Direction.ASC, "id","birthday"));


        //针对用户完成分页
        Page<UserBean> page = userServiceImpl.findUsersByParamsToPage(userName, age, password, pageable);

        System.out.println(page.hasPrevious());//是否有上一页
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.getNumber());//当前页码
        System.out.println(page.getTotalElements());//总共有多少条
        System.out.println(page.getContent());//具体的数据
        System.out.println(page.getSize());//每页显示的行数
        System.out.println(page.getTotalPages());//总页数
    }



    /**
     *  在spring data jpa框架中，不能直接传递对象，或者Map键值对      到@Query注解中去
     */

    @Test
    public void findUsersByParams() {
        List<?> datas = userServiceImpl.findUsersByParams("张",19,"123456");

        System.out.println(datas);
    }


    @Test
    public void findByUserNameContainingAndAge() {
        // 根据用户名StartingWith和年龄精准查询用户数据
        List<?> datas = userServiceImpl.findByUserNameContainingAndAge("张", 19);
        System.out.println(datas);
    }


    @Test
    public void findByUserNameAndAge() {
        // 根据用户名StartingWith和年龄精准查询用户数据
        List<?> datas = userServiceImpl.findByUserNameAndAge("张龙", 19);
        System.out.println(datas);
    }

    @Test
    public void findByUserNameStartingWithAndAge() {

        // 根据用户名StartingWith和年龄模糊查询用户数据
        List<?> datas = userServiceImpl.findByUserNameStartingWithAndAge("张", 19);
        System.out.println(datas);

        //除了可以使用StartingWith之外，还可以使用EndingWith，Containing

    }

    @Test
    public void testFindAll() {

        List<?> datas = userServiceImpl.findAll();
        System.out.println(datas);

        userServiceImpl.deleteUserInfoList((List<UserBean>) datas);

    }

    @Test
    public void testUpdateUserBean() {
        // 如果已经在web.xml中配置了OpenEntityManagerInViewFilter,修改代码如下：
		/*
		 * UserBean user = (UserBean) userServiceImpl.getUserInfoById(3l); //getOne(id)
		 * === load(UserBean,id); System.out.println(user); user.setAge(39);
		 * user.setLoginName("xiaowang"); userServiceImpl.updateUserInfo(user);
		 *
		 *
		 * 删除同理
		 */

        // 否则，代码如下：
        EntityManagerFactory factory = emf.getObject();
        EntityManager em = factory.createEntityManager();
        Session session = em.unwrap(Session.class);// 获得底层Session连接对象
        // 如果是生产环境中，要解决延迟加载中的no session问题，需要在web.xml中
        // 配置OpenEntityManagerInViewFilter == OpenSessionInViewFilter
        Transaction tx = session.beginTransaction();
        UserBean user = (UserBean) session.get(UserBean.class, 3l);
        System.out.println(user);

        user.setName("小明");
        user.setPwd("xiaowang");
        // userServiceImpl.updateUserInfo(user);
        session.update(user);
        tx.commit();

    }
}
