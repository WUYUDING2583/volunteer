package volunteer.po;

import java.io.Serializable;

public class ActReqPK implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getAno() {
		return Ano;
	}

	public ActReqPK(String ano, String atime, String ajobstate) {
		super();
		Ano = ano;
		Atime = atime;
		Ajobstate = ajobstate;
	}

	public ActReqPK(String ano, String atime) {
		super();
		Ano = ano;
		Atime = atime;
	}

	public void setAno(String ano) {
		Ano = ano;
	}

	public String getAtime() {
		return Atime;
	}

	public void setAtime(String atime) {
		Atime = atime;
	}

	public String getAjobstate() {
		return Ajobstate;
	}

	public void setAjobstate(String ajobstate) {
		Ajobstate = ajobstate;
	}

	private String Ano;
	private String Atime;
	private String Ajobstate;

	public ActReqPK() {
		// TODO 自动生成的构造函数存根
	}

}
