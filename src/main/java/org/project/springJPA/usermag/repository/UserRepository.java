package org.project.springJPA.usermag.repository;

import org.project.springJPA.beans.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserBean,Integer> {
    //String jpql = "from UserBean as u where u.userName like CONCAT(?1,'%') and age = ?2";
    public List<?> findByUserNameStartingWithAndAge(String userName, Integer age);

    //String jpql = "from UserBean as u where u.userName = ?1 and age = ?2";
    public List<?> findByUserNameAndAge(String userName,Integer age);

    //String jpql = "from UserBean as u where u.userName like CONCAT('%',?1,'%') and age = ?2";
    //Desc 表示降序， Asc表示升序
    public List<?> findByUserNameContainingAndAgeOrderByIdDesc(String userName,Integer age);

//	@Query(value="From UserBean as u where u.userName like CONCAT('%',:userName,'%') and u.age = :age and u.password = :password")
//	public List<?> findUsersByParams(@Param("userName")String userName,@Param("age")Integer age,@Param("password")String password);
//

    @Query(value="From UserBean as u where u.userName like CONCAT('%',?1,'%') and u.age = ?2 and u.password = ?3")
    public List<?> findUsersByParams(String userName,Integer age,String password);

    public Page<UserBean> findByUserNameContainingAndAgeAndPassword(String userName, Integer age, String password, Pageable pageable);


    @Modifying//改注解表示，Query注解中的语句 ，会对数据库造成修改
//	@Query(value="",nativeQuery=true) nativeQuery=true 表示Query注解中，直接放置的就是SQL
    @Query(value="update UserBean as u set u.userName = ?1,u.age=?2 where u.id = ?3")
    public void updateUserBean(String userName,Integer age,Long id);
}
