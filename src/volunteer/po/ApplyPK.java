package volunteer.po;

import java.io.Serializable;

public class ApplyPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ApplyPK() {
		// TODO 自动生成的构造函数存根
	}
	private String No;
	private String Ano;
	private String Ajobstate;
	private String Atime;
	public ApplyPK(String no, String ano, String ajobstate, String atime) {
		super();
		No = no;
		Ano = ano;
		Ajobstate = ajobstate;
		Atime = atime;
	}
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getAno() {
		return Ano;
	}
	public void setAno(String ano) {
		Ano = ano;
	}
	public String getAjobstate() {
		return Ajobstate;
	}
	public void setAjobstate(String ajobstate) {
		Ajobstate = ajobstate;
	}
	public String getAtime() {
		return Atime;
	}
	public void setAtime(String atime) {
		Atime = atime;
	}
}
