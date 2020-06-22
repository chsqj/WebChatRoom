package chat.Dao;

import chat.JavaBean.UserBean;

public interface UserDao {
	
	public UserBean findUser(String username);

	public void addUser(UserBean user);
}
