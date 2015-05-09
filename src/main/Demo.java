package main;

import dataTypes.Monomial;
import dataTypes.Polynomial;
import acm.program.ConsoleProgram;

public class Demo extends ConsoleProgram {

	public void run(){
		Monomial a=new Monomial();
		Monomial b=new Monomial();
		Polynomial more=new Polynomial();
		a.add("2");
		a.add("-2.5");
		a.add("x");
		a.add("x");
		b.add("5");
		b.add("x");
		b.add("x");
		println(a.toString());
		more.add(a);
		println(more.toString());
		more.add(b);
		println(more.toString());
		b.add("y");
		more.add(b);
		println(more.toString());
		more.add(a);
		println(more.toString());
	}

}
