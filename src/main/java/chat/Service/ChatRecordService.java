package chat.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.Dao.ChatRecordDao;
import chat.JavaBean.ChatRecordBean;

@Service
public class ChatRecordService {
	
	@Autowired
	ChatRecordDao chatRecordDao;
	
	public void addChatRecord(ChatRecordBean chatRecord){
		chatRecordDao.addChatRecord(chatRecord);
	}

	public ArrayList<ChatRecordBean> getChatRecord(String recipient, String username) {
		// TODO Auto-generated method stub
		return chatRecordDao.getChatRecord(recipient, username);
	}
}
