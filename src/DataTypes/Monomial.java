package DataTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Monomial extends ArrayList<String> {

	public Monomial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Monomial(Collection<? extends String> arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Monomial(int arg0) {
		super(arg0);
		for(int coeff=0;coeff<this.size();coeff++){
			this.set(coeff, "1");
		}
	}
	@Override
	public String toString(){
		return super.toString().replaceAll(",", "*");
	}
	
}
