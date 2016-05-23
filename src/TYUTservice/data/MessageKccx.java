package TYUTservice.data;

import java.util.List;

import TYUTservice.data.msgdata.KccxMsg;

public class MessageKccx extends MessageTyut {
	private List<KccxMsg> kccxMsgs;

	public List<KccxMsg> getKccxMsgs() {
		return kccxMsgs;
	}

	public void setKccxMsgs(List<KccxMsg> kccxMsgs) {
		this.kccxMsgs = kccxMsgs;
	}

	public MessageKccx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageKccx(int id, int status,List<KccxMsg> kccxMsgs) {
		super(id, status);
		this.kccxMsgs=kccxMsgs;
		// TODO Auto-generated constructor stub
	}
}
