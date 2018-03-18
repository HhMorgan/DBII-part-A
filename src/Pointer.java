import java.io.Serializable;

public class Pointer implements Serializable {
	
	int page;
	int index;
	
	public Pointer(int page, int index)
	{
		this.page = page;
		this.index = index;
	}
	public String toString(){
		return "page : "+page+", index : "+ index;
	}
}
