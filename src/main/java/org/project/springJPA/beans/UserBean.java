package org.project.springJPA.beans;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="t_user")
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class UserBean implements Serializable {
    @Id
    @Column(name="id")
    //strategy="identity" 引号中间的内容，为hibernate的id生成策略
    @GenericGenerator(name="hibernate.strategy", strategy="identity")
    @GeneratedValue(generator="hibernate.strategy")
    private int id;

    @Column
    private String name;
    @Column
    private String pwd;
    @Column
    private String sex;
    @Version
    private Integer version;
    public UserBean() {
    }

    public UserBean(String name, String pwd, String sex, Integer version) {
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", version=" + version +
                '}';
    }
}
