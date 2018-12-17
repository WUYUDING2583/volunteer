package volunteer.po;

import java.io.Serializable;
import java.util.List;

public class Vtime implements Serializable {
	private static final long serialVersionUID = 4208335001425955600L;
	private float Allvtime;
	private String name;
	private List<ManHour> manhour;
	public List<ManHour> getManhour() {
		return manhour;
	}
	public void setManhour(List<ManHour> manhour) {
		this.manhour = manhour;
	}
	public Vtime() {}
	public void setAllvtime(float Allvtime) {this.Allvtime=Allvtime;}
	public float getAllvtime() {return Allvtime;}
}
