package dataTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Monomial extends ArrayList<String> {
	public double coeff=1;
	public Monomial() {
		super();
		coeff=1;
	}

	public Monomial(Collection<? extends String> arg0) {
		super(arg0);
		coeff=1;
		// TODO Auto-generated constructor stub
	}

	public Monomial(int arg0) {
		super(arg0);
		coeff=1;
		//for(int coeff=0;coeff<this.size();coeff++){
		//	this.set(coeff, "1");
		//}
	}
	@Override
	public boolean add(String item){
		try{
			Double.parseDouble(item);
			this.coeff=Double.parseDouble(item)*coeff;
			return true;
		}catch(NumberFormatException e){
			return super.add(item);
		}
	}
	@Override
	public String toString(){
		ArrayList<String> withCoeff=new ArrayList<String>();
		withCoeff.addAll(this);
		withCoeff.add(0, coeff+"");
		String str=withCoeff.toString().replaceAll(", ", "");
		str=str.replaceAll("\\[", "").replaceAll("\\]", "");
		return str;
	}
	
}
