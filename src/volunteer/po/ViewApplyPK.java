package volunteer.po;

import java.io.Serializable;

public class ViewApplyPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ViewApplyPK() {
		// TODO 自动生成的构造函数存根
	}
	private String Ano;
	private String Aname;
	private String Adeadline;
	public ViewApplyPK(String ano, String aname, String adeadline) {
		super();
		Ano = ano;
		Aname = aname;
		Adeadline = adeadline;
	}
	public String getAno() {
		return Ano;
	}
	public void setAno(String ano) {
		Ano = ano;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getAdeadline() {
		return Adeadline;
	}
	public void setAdeadline(String adeadline) {
		Adeadline = adeadline;
	}
}
