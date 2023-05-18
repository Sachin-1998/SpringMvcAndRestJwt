package serverSideformValidation.entity;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginData 
{
	@NotBlank(message="User Name can not be empty ??")
	@Size(min=4,max=15,message="User Name must between 4 to 15 characters ??")
	private String userName;
    @Email (regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Invalid Mail Id ??" )
	private String email;
	@AssertTrue(message="Please Accept the Terms and Conditions !!")
	private boolean agreed;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public boolean isAgreed() {
		return agreed;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	@Override
	public String toString() {
		return "LoginData [userName=" + userName + ", email=" + email + "]";
	}
	
    
}
