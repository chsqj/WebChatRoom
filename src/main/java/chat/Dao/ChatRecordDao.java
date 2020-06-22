package chat.Dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import chat.JavaBean.ChatRecordBean;

public interface ChatRecordDao {
	
	public void addChatRecord(ChatRecordBean chatRecord);

	public ArrayList<ChatRecordBean> getChatRecord(@Param("recipient")String recipient, @Param("username")String username);
	
}
