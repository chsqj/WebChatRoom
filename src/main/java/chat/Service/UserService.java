package chat.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.Dao.UserDao;
import chat.JavaBean.UserBean;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	//查看用户是否存在于数据库
	public boolean isExist(UserBean user) {
		System.out.println("come in service"+user.getUsername());
		UserBean userBean = userDao.findUser(user.getUsername());
		System.out.println("come out service");
		return userBean.getPassword().equals(user.getPassword());
	}
	
	//注册添加用户
	public void addUser(UserBean user) {
		userDao.addUser(user);
	}
	
	//查看用户是否可以添加（用户名是否已存在）
	public boolean isCanAdd(UserBean user) {
		UserBean userBean = userDao.findUser(user.getUsername());
		return userBean == null;
	}
}
