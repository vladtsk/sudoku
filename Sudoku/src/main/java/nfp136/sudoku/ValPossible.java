package nfp136.sudoku;

public class ValPossible {
	
	int ligne1;
	int col1;
	int sousCarre1;
	
	int ligne2;
	int col2;
	int sousCarre2;
	
	int value;
	
	boolean updatedFirst = false;
	
	public ValPossible(int value, int ligne1, int col1, int sousCarre1, int ligne2, int col2, int sousCarre2) { //int sous_carre
		this.ligne1= ligne1;
		this.col1 = col1;
		this.sousCarre1 = sousCarre1;
		
		this.ligne2= ligne2;
		this.col2 = col2;
		this.sousCarre2 = sousCarre2;
		
		this.value = value;
		//this.sous_carre = sous_carre;
	}
	
	
	public String toString() {
		return "{l1 : " + ligne1 + ", c1 : " + col1 + ", l2: " + + ligne2 + ", c2 : " + col2 + ", val: "+ value + "}";
	}
	
	
	
}
