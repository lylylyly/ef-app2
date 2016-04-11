package com.efeiyi.ec.community.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ming800.core.base.model.BaseUser;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ming
 * Date: 13-3-6
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "organization_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class MyUser implements Serializable, UserDetails ,BaseUser {

    private String id;
    private String username;
    private String name;
    private String name2;    //别名
    private String password;
    private Integer status;      // 0假删  1粉丝 2匠人
    private Role role;
    private String confirmPassword;
    private String oldPassword;
    private boolean enabled;               //是否可用
    private boolean accountExpired;       //账号过期
    private boolean accountLocked;        //账号锁定
    private boolean credentialsExpired;  //证书过期
    private String tempPageUrl;
    private Map<String, String> settingMap;
    private Integer utype; //总部用户，分公司用户
    private Date lastLoginDatetime;//最后一次登陆时间
    private Date lastLogoutDatetime;//最后一次登陆时间
    protected Date createDatetime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "type")
    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    @Id
    @GenericGenerator(name = "id", strategy = "com.ming800.core.p.util.M8idGenerator")
    @GeneratedValue(generator = "id")
    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "truename")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "account_enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "account_expired")
    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    @Column(name = "account_locked")
    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @Column(name = "credentials_expited")
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @JsonIgnore
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
      if(role!=null) {
          Permission rolePermission = new Permission();//角色即权限
          rolePermission.setAuthority(role.getName());
          gList.add(rolePermission);
//          if (role.getPermissionsList() != null) {
//              for (Permission permission : role.getPermissionsList()) {
//                  gList.add(permission);
//              }
//          }
      }
        Permission defaultPermission = new Permission();//每个用户都默认带ROLE_USER这个权限
        defaultPermission.setAuthority("ROLE_USER");//普通用户
        gList.add(defaultPermission);
        return gList;
    }



    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Transient
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Transient
    public String getFullName() {
        return this.name + "[" + this.username + "]";
    }

    @Override
    public String toString() {
        return "User{id = " + id + ", username = " + username + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       /* BigUser user = (BigUser) o;

        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
*/
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Column(name = "truename2")
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @JsonIgnore
    @Transient
    public String getTempPageUrl() {
        return tempPageUrl;
    }

    public void setTempPageUrl(String tempPageUrl) {
        this.tempPageUrl = tempPageUrl;
    }

    @JsonIgnore
    @Transient
    public Map<String, String> getSettingMap() {
        return settingMap;
    }

    public void setSettingMap(Map<String, String> settingMap) {
        this.settingMap = settingMap;
    }

    @Column(name = "last_login_datetime")
    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }
    @Transient
    public Date getLastLogoutDatetime() {
        return lastLogoutDatetime;
    }

    public void setLastLogoutDatetime(Date lastLogoutDatetime) {
        this.lastLogoutDatetime = lastLogoutDatetime;
    }

    @Column(name = "create_datetime")
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
