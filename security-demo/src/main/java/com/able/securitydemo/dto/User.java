package com.able.securitydemo.dto;

import com.able.securitydemo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor

public class User {

    public interface  UserSimpleView{};
    public interface UserDetialView extends UserSimpleView{};

    @JsonView(UserSimpleView.class)
    private Integer id;
    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "不能为admin")
    private String username;
    @JsonView(UserDetialView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
