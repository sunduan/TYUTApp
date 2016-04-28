package TYUTservice.data;

public class MessageTyut {
	public MessageTyut(){
		
	}
	public MessageTyut(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	// id： 1.登录,2.课程查询,3.方案成绩,4.培养方案,5.考试管理,6.教学评估,7.个人管理....等等
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
