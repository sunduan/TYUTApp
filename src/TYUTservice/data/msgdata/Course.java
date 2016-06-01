package TYUTservice.data.msgdata;

public class Course {
	  private String name;//课程名称、上课教室，教师，课程编号  
	    int start,step; //开始上课节次， 一共几节课  
	    public Course(String name,  int start, int step 
	          ) {  
	        super();   
	        this.name=name;
	        this.start = start;  
	        this.step = step;  

	    }  
	  

	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  

	  
	    public int getStart() {  
	        return start;  
	    }  
	  
	    public void setStart(int start) {  
	        this.start = start;  
	    }  
	  
	    public int getStep() {  
	        return step;  
	    }  
	  
	    public void setStep(int step) {  
	        this.step = step;  
	    }  
	  
	      
}
