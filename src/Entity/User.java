
package Entity;

import java.util.Date;

/**
 *
 * @author PC
 */
public class User {
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean role;
    private Date createAt;
    private Date updateAt;

    public User(String id, String password, String name, String phone, String email, boolean role, Date createAt, Date updateAt) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User(String password, String name, String phone, String email) {
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User(String id, String password, String name, String phone, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone + ", email=" + email + ", role=" + role + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }

    
    
    
}
