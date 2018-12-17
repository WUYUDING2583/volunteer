package volunteer.po;
import java.io.Serializable;
public class Apply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Apply() {
	}
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private ApplyPK pk;
	private String Aname;
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ApplyPK getPk() {
		return pk;
	}
	public void setPk(ApplyPK pk) {
		this.pk = pk;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public Apply(ApplyPK pk, String aname) {
		super();
		this.pk = pk;
		Aname = aname;
	}
	

}
