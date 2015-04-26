package dataTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Monomial extends ArrayList<String> {

	public Monomial() {
		super();
		super.add("1");

	}

	public Monomial(Collection<? extends String> arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Monomial(int arg0) {
		super(arg0);
		super.add("1");
		//for(int coeff=0;coeff<this.size();coeff++){
		//	this.set(coeff, "1");
		//}
	}
	@Override
	public boolean add(String item){
		try{
			Double.parseDouble(item);
			return this.set(0,""+ Double.parseDouble(item)*Double.parseDouble(this.get(0)))!=null;
		}catch(NumberFormatException e){
			return super.add(item);
		}
	}
	@Override
	public String toString(){
		String str=super.toString().replaceAll(", ", "*");
		str=str.replaceAll("\\[", "").replaceAll("\\]", "");
		return str;
	}
	
}
