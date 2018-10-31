package volunteer.modal;

import java.io.Serializable;

public class ActReq  implements Serializable{
	private String Ano;
	private String Atime;
	private String Ajobstate;
	private int Ajobcount;
	private int DoneAccount;
	
	public ActReq() {}
	
	public void setAno(String Ano) {this.Ano=Ano;}
	public void setAtime(String Atime) {this.Atime=Atime;}
	public void setAjobstate(String Ajobstate) {this.Ajobstate=Ajobstate;}
	public void setAjobcount(int Ajobcount) {this.Ajobcount=Ajobcount;}
	public void setDoneAccount(int DoneAccount) {this.DoneAccount=DoneAccount;}
	
	public String getAno() {return Ano;}
	public String getAtime() {return Atime;}
	public String getAjobstate() {return Ajobstate;}
	public int getAjobcount() {return Ajobcount;}
	public int getDoneAccount() {return DoneAccount;}
}
