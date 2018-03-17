import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class nonClustering implements Serializable, Comparable<nonClustering>{
    Object value;
    ArrayList<Pointer> pointers;
	
    public nonClustering(Object v,ArrayList<Pointer> p){
    	value =v;
    	pointers = p;
    }
	
	
	
	
	@Override
	public int compareTo(nonClustering arg0) {
		String type = this.value.getClass().toString();
		type =type.substring(6, type.length());
		if(type.equals("java.lang.Double")){
		return ((Double)(value)).compareTo((Double)(arg0.value));}
		else if(type.equals("java.lang.Integer")){
			return ((Integer)(value)).compareTo((Integer)(arg0.value));}
		else if(type.toString().equals("java.util.Date")){
			return ((Date)(value)).compareTo((Date)(arg0.value));}
		return ((String)(value)).compareTo((String)(arg0.value));
	}




	public ArrayList<Pointer> getPointers() {
		return pointers;
	}
	
	

}
