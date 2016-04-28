package TYUTservice.data;

import java.util.List;

public class MessageFacj extends MessageTyut {

	private List<FacjMsg> facjMsgs;

	public List<FacjMsg> getFacjMsgs() {
		return facjMsgs;
	}

	public MessageFacj(int id, int status, List<FacjMsg> facjMsgs) {
		super(id, status);
		this.facjMsgs = facjMsgs;
		// TODO Auto-generated constructor stub
	}

	public void setFacjMsgs(List<FacjMsg> facjMsgs) {
		this.facjMsgs = facjMsgs;
	}

	public MessageFacj() {

	}

}
