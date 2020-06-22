package chat.Controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import chat.JavaBean.UserBean;
import chat.Service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/user/record")
	public ModelAndView record(UserBean user, HttpSession session) {
		session.setAttribute("username", user.getUsername());//session中放入用户名信息
		if(userService.isExist(user)){
			return new ModelAndView("redirect:/return/success.html?username="+user.getUsername());
		}else{
			return new ModelAndView("/return/fail.html");
		}
	}
	
	@RequestMapping("/user/registering")
	public String register(UserBean user) {
		if(!userService.isCanAdd(user)) {
			return "/user/register.html";
		}
		userService.addUser(user);
		return "/user/welcome.html";
	}
}
