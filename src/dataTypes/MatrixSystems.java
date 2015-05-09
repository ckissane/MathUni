package dataTypes;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.mbertoli.jfep.ExpressionNode;
import org.mbertoli.jfep.Parser;

import acm.program.ConsoleProgram;

public class MatrixSystems extends ConsoleProgram {
	ArrayList<ArrayList<String>> expressions=new ArrayList<ArrayList<String>>();
	public void run() {
		String[] splitEx=readLine("coeffs "+(1)+":").split(" ");
		for(int i=1;i<splitEx.length;i++){
			expressions.add(new ArrayList<String>(Arrays.asList(splitEx)));
			splitEx=readLine("coeffs "+(i+1)+":").split(" ");
		}
		expressions.add(new ArrayList<String>(Arrays.asList(splitEx)));
		
		println(new Matrix(splitEx.length).makeFromString(expressions).toString());
		println("m1,1");
		println(new Matrix(splitEx.length).makeFromString(expressions).getMinor(0, 0).toString());
		println("adju");
		println(new Matrix(splitEx.length).makeFromString(expressions).getAdjugate().toString());
		println("inver");
		println(new Matrix(splitEx.length).makeFromString(expressions).getInverse().toString());
		println("mult");
		println(new Matrix(splitEx.length).makeFromString(expressions).multiply(
				new Matrix(splitEx.length).makeFromString(expressions).getInverse()).toString());
		println("deter");
		println(getDeterminant(expressions));
		println("minor matrix");
		println(new Matrix(splitEx.length).makeFromString(expressions).getMinorMatrix().toString());
		println("cofact matrix");
		println(new Matrix(splitEx.length).makeFromString(expressions).getCofactorMatrix().toString());
		println("transposed matrix");
		println(new Matrix(splitEx.length).makeFromString(expressions).getTransposed().toString());
	}
	
	private void printMatrix(){
		int longest=0;
		for(ArrayList<String> equation:expressions){
			if(equation.toString().replace(" ", "").length()>longest){
				longest=equation.toString().replace(" ", "").length();
			}
		}
		for(int i=-2;i<longest;i++){
			print("-");
		}
		println();
		for(ArrayList<String> equation:expressions){
			print("| ");
			String printIt=equation.toString().replace(",", "").substring(1, equation.toString().replace(",", "").length()-1);
			print(printIt);
			for(int i=printIt.length()+2;i<longest;i++){
				print(" ");
			}
			println(" |");
		}
	
	}
	private double getDeterminant(ArrayList<ArrayList<String>> matrix){
		double sum=0;
		if(matrix.size()==1){
			return Double.parseDouble(matrix.get(0).get(0));
		}
		for(int x=0;x<matrix.size();x++){
			ArrayList<ArrayList<String>> newArray=new ArrayList<ArrayList<String>>();
			for(ArrayList<String> item:matrix){
				newArray.add((ArrayList<String>) item.clone());
			}
			for(ArrayList<String> row:newArray){
				row.remove(x);
			}
			newArray.remove(0);
			if(x%2==0){
				sum+=getDeterminant(newArray)*Double.parseDouble(matrix.get(0).get(x));
			}else{
				sum-=getDeterminant(newArray)*Double.parseDouble(matrix.get(0).get(x));
			}
		}
		return sum;
		
	}
	
}
