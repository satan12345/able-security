/**
 * 
 */
package com.able.securitydemo.controller;

import com.able.securitydemo.dto.User;
import com.able.securitydemo.exception.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@GetMapping("")
	@JsonView(User.UserSimpleView.class)
	public List<User> query(String username){
		log.error("username= {}",username);

		return Lists.newArrayList(new User(1,"旗木卡卡西","123456"));
	}
	@GetMapping("{id:\\d+}")
	@JsonView(User.UserDetialView.class)
	public User queryUserInfo(@PathVariable("id")Integer id){
		User user=new User();
		user.setUsername("宇智波哟");
		user.setPassword("2346");
		return user;
//		throw new RuntimeException("抛出异常咯");
//		throw new UserNotFoundException(id.toString());

	}
	@PostMapping
	public User create(@Valid User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(x-> System.out.println(x));
		}
		log.info("username={}",user.getUsername());
		log.info("password={}",user.getPassword());
		user.setId(1);
		return user;
	}
	@PutMapping("{id}")
	public User update(@PathVariable("id") Integer id,
					   @Valid User user,
					   BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(x->{
				FieldError fieldError= (FieldError) x;
				String msg=fieldError.getField()+":"+fieldError.getDefaultMessage();
				System.err.println(msg);
			});
		}
		user.setId(id);
		return user;
	}

	@DeleteMapping("{id}")
	public User delete(@PathVariable("id") Integer id){
		User user=new User(2,"宇智波鼬","654321");
		return user;
	}

}
