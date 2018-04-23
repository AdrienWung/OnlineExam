package online.exam.datacenter.model;


public class Student {
    private String email;

    public Student(String email) {
        this.email = email;
    }

    public Student() {
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [email=" + email + "]";
	}
    
    
}
