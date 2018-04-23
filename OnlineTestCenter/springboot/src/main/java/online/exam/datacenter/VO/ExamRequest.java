package online.exam.datacenter.VO;


public class ExamRequest {
    private int examID;
    private String email;
    private String password;
	public int getExamID() {
		return examID;
	}
	public void setExamID(int examID) {
		this.examID = examID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "ExamRequest [examID=" + examID + ", email=" + email + ", password=" + password + "]";
	}
    
}
