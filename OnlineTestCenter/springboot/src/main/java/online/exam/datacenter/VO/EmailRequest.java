package online.exam.datacenter.VO;

public class EmailRequest {
	public String email;
	public String title;
	public String content;
	public EmailRequest(String email, String title, String content) {
		super();
		this.email = email;
		this.title = title;
		this.content = content;
	}
	public EmailRequest() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "EmailRequest [email=" + email + ", title=" + title + ", content=" + content + "]";
	}
	
	

}
