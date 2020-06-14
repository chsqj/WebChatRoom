package chat.JavaBean;

public class ChatRecordBean {
	private int id;
	private String username;
	private String recipient;
	private String sentence;
	
	public ChatRecordBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ChatRecordBean(String username, String recipient, String sentence) {
		this.username = username;
		this.recipient = recipient;
		this.sentence = sentence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	
}
