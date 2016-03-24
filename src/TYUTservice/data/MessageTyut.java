package TYUTservice.data;

public class MessageTyut {
	public MessageTyut(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	// 信息id 1.登录,2.获取课程表....等等
	private int id;
	// 信息获取状态1.失败1，2失败2，3成功。。。。
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
