package dataTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Polynomial extends ArrayList<Monomial> {
	private ArrayList<Double> coeffs;
	public Polynomial() {
		super();
		coeffs=new ArrayList<Double>();
	}

	public Polynomial(Collection<? extends Monomial> arg0) {
		super(arg0.size());
		coeffs=new ArrayList<Double>();
		for(Monomial m:arg0){
			coeffs.add(m.coeff);
			m.coeff=1;
			super.add(m);
			
		}
		// TODO Auto-generated constructor stub
	}

	public Polynomial(int arg0) {
		super(arg0);
		coeffs=new ArrayList<Double>(arg0);
		//for(int coeff=0;coeff<this.size();coeff++){
		//	this.set(coeff, "1");
		//}
	}
	@Override
	public boolean add(Monomial item){
		
		if(this.contains(new Monomial(item))){
			int index=this.indexOf(new Monomial(item));
			this.coeffs.set(index,this.coeffs.get(index)+item.coeff);
			removeZeros();
			return true;
		}else{
			Monomial m=new Monomial(item);
			coeffs.add(item.coeff);
			removeZeros();
			return super.add(m);
		}
	}
	public void removeZeros(){
		for(int i=0;i<coeffs.size();i++){
			if(coeffs.get(i)==0){
				coeffs.remove(i);
				this.remove(i);
				i--;
			}
		}
	}
	@Override
	public String toString(){
		ArrayList<Monomial> withCoeffs=new ArrayList<Monomial>(this);
		for(Monomial m:withCoeffs){
			m.coeff=this.coeffs.get(withCoeffs.indexOf(m));
		}
		String str=withCoeffs.toString().replaceAll(", ", "+");
		str=str.replaceAll("\\[", "").replaceAll("\\]", "");
		if(str.isEmpty()){
			return "0";
		}
		return str;
	}
	
}
