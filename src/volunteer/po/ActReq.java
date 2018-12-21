package volunteer.po;

import java.io.Serializable;

import org.json.JSONObject;

public class ActReq  implements Serializable{
	private static final long serialVersionUID = 1L;
	private ActReqPK pk;
	private int Ajobcount;
	private int DoneAccount=0;
	private ActInfo act;
	public ActInfo getAct() {
		return act;
	}
	public void setAct(ActInfo act) {
		this.act = act;
	}
	public ActReqPK getPk() {
		return pk;
	}
	public void setPk(ActReqPK pk) {
		this.pk = pk;
	}
	public ActReq() {}
	public void setAjobcount(int Ajobcount) {this.Ajobcount=Ajobcount;}
	public void setDoneAccount(int DoneAccount) {this.DoneAccount=DoneAccount;}
	public int getAjobcount() {return Ajobcount;}
	public int getDoneAccount() {return DoneAccount;}
	public JSONObject toJson()
	{
		JSONObject json=new JSONObject();
		//json.put("Ano",pk.getAno().trim());
		json.put("Ajobstate", pk.getAjobstate());
		json.put("Atime", pk.getAtime());
		json.put("Ajobcount", Ajobcount);
		json.put("DoneAccount", DoneAccount);
		System.out.println("ACTREQ:"+json.toString());
		return json;
	}
	
}
