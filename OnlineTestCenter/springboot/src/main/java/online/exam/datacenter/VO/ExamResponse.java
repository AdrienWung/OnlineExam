package online.exam.datacenter.VO;


public class ExamResponse {
    private String status;
    private int examID;
    private String password;
    private String examName;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getExamID() {
		return examID;
	}
	public void setExamID(int examID) {
		this.examID = examID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	@Override
	public String toString() {
		return "ExamResponse [status=" + status + ", examID=" + examID + ", password=" + password + ", examName="
				+ examName + "]";
	}
    
    
}
