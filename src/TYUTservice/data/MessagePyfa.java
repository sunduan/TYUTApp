package TYUTservice.data;

import java.util.List;

import TYUTservice.data.msgdata.PyfaMsg;

public class MessagePyfa extends MessageTyut {
	private List<PyfaMsg> pyfaMsgs;
	public MessagePyfa(){
		super();
	}
	public List<PyfaMsg> getPyfaMsgs() {
		return pyfaMsgs;
	}
	public void setPyfaMsgs(List<PyfaMsg> pyfaMsgs) {
		this.pyfaMsgs = pyfaMsgs;
	}
	public MessagePyfa(int id, int status,List<PyfaMsg> pyfaMsgs) {
		super(id, status);
		this.pyfaMsgs=pyfaMsgs;
		// TODO Auto-generated constructor stub
	}
}
