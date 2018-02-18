
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Tuple implements Serializable, Comparable<Tuple> {
	ArrayList<Object> tupleData;
    Object key;
	public Tuple() {
		this.tupleData = new ArrayList<Object>();
	}

	public void add(Object o) {
		tupleData.add(o);
	}

	public Object remove(Object o) {
		return tupleData.remove(o);
	}

	@Override
	public int compareTo(Tuple arg0) {//checks the type of key and calls its compareto method respectively
		String type = this.key.getClass().toString();
		type =type.substring(6, type.length());
		if(type.equals("java.lang.Double")){
		return ((Double)(key)).compareTo((Double)(arg0.key));}
		else if(type.equals("java.lang.Integer")){
			return ((Integer)(key)).compareTo((Integer)(arg0.key));}
		else if(type.toString().equals("java.util.Date")){
			return ((Date)(key)).compareTo((Date)(arg0.key));}
		return ((String)(key)).compareTo((String)(arg0.key));
			
	}
	public void setKey(Object o){//setting the key when finding it
		key =o;
	}
	
}