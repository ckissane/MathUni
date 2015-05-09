package dataTypes;

import java.util.ArrayList;

public class Matrix extends ArrayList<ArrayList<Double>> {
	public Matrix makeFromString(ArrayList<ArrayList<String>> stringMatrix){
		this.removeRange(0, this.size());
		ArrayList<ArrayList<Double>> newArray=new ArrayList<ArrayList<Double>>();
		for(ArrayList<?> row:stringMatrix){
			ArrayList<Double> newRow=new ArrayList<Double>(stringMatrix.get(0).size());
			for(Object item:row){
				newRow.add(Double.parseDouble(item.toString()));
			}
			this.add(newRow);
		}
		return this;
	}
	public Matrix(ArrayList<ArrayList<Double>> stringMatrix) {
		this.addAll(stringMatrix);
	}
	public Matrix(Matrix matrix) {
		this.addAll(matrix.clone());
	}
	public int getWidth(){
		return this.get(0).size();
	}
	public int getHeight(){
		return this.size();
	}
	public Matrix(int rows,int cols) {
		super(rows);
		for(int r=0;r<rows;r++){
			ArrayList<Double> row=new ArrayList<Double>(cols);
			for(int c=0;c<cols;c++){
				row.add(1.0);
			}
			this.add(row);
		}
		
	}
	public Matrix(int size) {
		super(size);
		for(int r=0;r<size;r++){
			ArrayList<Double> row=new ArrayList<Double>(size);
			for(int c=0;c<size;c++){
				row.add(1.0);
			}
			this.add(row);
		}
		
	}
	public ArrayList<Double>  getRow(int rowNum){
		return this.get(rowNum);
	}
	
	public ArrayList<Double>  getColumn(int colNum){
		ArrayList<Double> column=new ArrayList<Double>(this.size());
		for(ArrayList<Double> row:this){
			column.add(row.get(colNum));
		}
		return column;
	}
	
	public double getDeterminant(){
		/*double sum=0;
		if(size()==1){
			return get(0).get(0);
		}
		for(int x=0;x<size();x++){
			ArrayList<ArrayList<Double>> newArray=new ArrayList<ArrayList<Double>>();
			for(ArrayList<Double> item:this){
				newArray.add((ArrayList<Double>) item.clone());
			}
			for(ArrayList<Double> row:newArray){
				row.remove(x);
			}
			newArray.remove(0);
			if(x%2==0){
				sum+=getDeterminant(new Matrix(newArray))*get(0).get(x);
			}else{
				sum-=getDeterminant(new Matrix(newArray))*get(0).get(x);
			}
		}*/
		return getDeterminant(this);
		
	}
	
	private double getDeterminant(Matrix matrix){
		double sum=0;
		if(matrix.getHeight()==1){
			return matrix.get(0).get(0);
		}
		for(int x=0;x<matrix.getWidth();x++){
			if(x%2==0){
				sum+=getDeterminant(matrix.getMinor(0,x))*matrix.get(0).get(x);
			}else{
				sum-=getDeterminant(matrix.getMinor(0,x))*matrix.get(0).get(x);
			}
		}
		return sum;
		
	}
	public Matrix getAdjugate(Matrix matrix){
		double sum=0;
		if(matrix.getHeight()==2){
			Matrix returnMa=matrix.clone();
			double a=returnMa.get(0).get(0);
			returnMa.get(0).set(0, matrix.get(1).get(1));
			returnMa.get(1).set(1, a);
			returnMa.get(0).set(1, -matrix.get(0).get(1));
			returnMa.get(1).set(0, -matrix.get(1).get(0));
			return returnMa;
		}else{
			Matrix returnMa=(Matrix) matrix.clone();
			for(int r=0;r<this.getHeight();r++){
				for(int c=0;c<this.getWidth();c++){
					returnMa.set(r,c, matrix.getMinor(r, c).getDeterminant());
				}
			}
			return matrix.clone().getCofactorMatrix().getTransposed();
		}
		
	}
	public Matrix clone(){
		Matrix returnMa=new Matrix(this.getHeight(),this.getWidth());
		for(int r=0;r<this.getHeight();r++){
			for(int c=0;c<this.getWidth();c++){
				returnMa.set(r,c, this.get(r).get(c));
			}
		}
		//return (Matrix)  new Matrix((ArrayList<ArrayList<Double>>) super.clone());
		return returnMa;
	}
	public Matrix multiply(Matrix matrix){
		double sum=0;
		Matrix returnMa=new Matrix(this.getHeight(),matrix.getWidth());
		for(int r=0;r<this.getHeight();r++){
			for(int c=0;c<matrix.getWidth();c++){
				System.out.println(r+","+c);
				double total=0;
				for(int i=0;i<matrix.getHeight();i++){
					total+=matrix.getColumn(c).get(i)*this.getRow(r).get(i);
				}
				returnMa.set(r, c, total);
				System.out.println(total);
			}
		}
		return returnMa;
		
	}
	public Matrix getAdjugate(){
		return getAdjugate(this);
	}
	public Matrix getInverse(){
		return this.getAdjugate().divide(this.getDeterminant());
	}
	public Matrix divide(double divisor) {
		Matrix returnMa=this.clone();
		/*for(int r=0;r<this.getHeight();r++){
			for(int c=0;c<this.getWidth();c++){
				returnMa.set(r,c, this.get(r).get(c)/divisor);
			}
		}*/
		for(ArrayList<Double> row:returnMa){
			for(Double item:row){
				item=item/divisor;
			}
		}
		
		return returnMa;
	}
	public void removeColumn(int column){
		for(ArrayList<Double> row:this){
			row.remove(column);
		}
	}
	public void removeRow(int row){
		this.remove(row);
	}
	public Matrix getMinor(int row,int column){
		Matrix returnMat =new Matrix(this);
		/*int cr=0;
		for(int r=0;r<this.getHeight();r++){
			if(r!=row){
				int cc=0;
				for(int c=0;c<this.getWidth();c++){
					if(c!=column){
						returnMat.set(cr, cc, this.get(r).get(c));
						cc++;
					}
				}
				cr++;
			}
		}*/
		returnMat.removeColumn(column);
		returnMat.remove(row);
		return returnMat;
	}
	public Matrix getMinorMatrix(){
		Matrix returnMat =this.clone();
		for(int r=0;r<this.getHeight();r++){
			for(int c=0;c<this.getWidth();c++){
				returnMat.set(r, c, this.getMinor(r, c).getDeterminant());
			}
		}
		return returnMat;
	}
	
	public Matrix getCofactorMatrix(){
		Matrix returnMat =this.clone().getMinorMatrix();
		for(int r=0;r<returnMat.getHeight();r++){
			for(int c=0;c<returnMat.getWidth();c++){
				returnMat.set(r, c, returnMat.get(r).get(c)*Math.pow(-1, r+c+2));
			}
		}
		return returnMat;
	}
	public Matrix getTransposed(){
		Matrix returnMat =new Matrix(this.getWidth(),this.getHeight());
		for(int r=0;r<returnMat.getHeight();r++){
			for(int c=0;c<returnMat.getWidth();c++){
				returnMat.set(r, c, this.get(c).get(r));
			}
		}
		return returnMat;
	}
	
	public void set(int row,int column,double value){
		this.get(row).set(column, value);
	}
	@Override
	public String toString(){
		String returnStr="";
		ArrayList<Integer> longestNumbers=new ArrayList<Integer>(this.get(0).size());
		for(int i=0;i<this.getRow(0).size();i++){
			longestNumbers.add(0);
		}
		for(ArrayList<Double> row:this){
			for(int i=0;i<row.size();i++){
				if(longestNumbers.get(i)<(row.get(i)+"").length()){
					longestNumbers.set(i,(row.get(i)+"").length());
				}
			}
		}
		
		for(ArrayList<Double> row:this){
			returnStr=returnStr+"|";
			for(int i=0;i<row.size();i++){
				returnStr=returnStr+row.get(i);
				for(int s=(row.get(i)+"").length();s<longestNumbers.get(i);s++){
					returnStr=returnStr+" ";
				}
				returnStr=returnStr+" ";
			}
			returnStr=returnStr.substring(0, returnStr.length()-1);
			returnStr=returnStr+"|\n";
		}
		returnStr=returnStr.substring(0, returnStr.length()-1);
		
		return returnStr;
		
	}
}
