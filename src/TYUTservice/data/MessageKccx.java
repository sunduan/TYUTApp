package TYUTservice.data;

import java.util.HashMap;
import java.util.List;

import TYUTservice.data.msgdata.Course;
import TYUTservice.data.msgdata.KccxMsg;

public class MessageKccx extends MessageTyut {
	private List<KccxMsg> kccxMsgs;
	private HashMap<String, HashMap<String, Course>> courses;
	public HashMap<String, HashMap<String, Course>> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<String, HashMap<String, Course>> courses) {
		this.courses = courses;
	}

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
