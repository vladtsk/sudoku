package nfp136.sudoku;

import java.util.ArrayList;
//import java.util.Arrays;

public class Case {

	int ligne;
	int colonne;
	int value;
	int sousCarre;
	
	ArrayList<Integer> valeursPossibles = new ArrayList<>();
	
	//private int[] forbiddenElements;
	
	public Case(int ligne, int colonne) {
		this.ligne= ligne;
		this.colonne = colonne;
		
		if(ligne > 0 && ligne <=3 && colonne > 0 && colonne <= 3) {
			sousCarre = 1;
		} else if(ligne > 0 && ligne <=3 && colonne > 3 && colonne <= 6) {
			sousCarre = 2;
		} else if(ligne > 0 && ligne <=3 && colonne > 6 && colonne <= 9) {
			sousCarre = 3;
		} else if(ligne > 3 && ligne <=6 && colonne > 0 && colonne <= 3) {
			sousCarre = 4;
		} else if(ligne > 3 && ligne <=6 && colonne > 3 && colonne <= 6) {
			sousCarre = 5;
		} else if(ligne > 3 && ligne <=6 && colonne > 6 && colonne <= 9) {
			sousCarre = 6;
		} else if(ligne > 6 && ligne <=9 && colonne > 0 && colonne <= 3) {
			sousCarre = 7;
		} else if(ligne > 6 && ligne <=9 && colonne > 3 && colonne <= 6) {
			sousCarre = 8;
		} else {
			sousCarre = 9;
		}
		
		
	}
	
	public ArrayList<Integer> getValeursPossibles() {
		return valeursPossibles;
	}
	
	public void updateValeursPossibles(int el) { // ArrayList<Integer> list
		/*for(int el : list) {
			valeursPossibles.add(el);
		}*/
		
		if(!valeursPossibles.contains(el)) valeursPossibles.add(el);
		
		
		System.out.println("Updating valeursPossibles with " + el);
	}
	
	public void removeValeurPossible(int el) {
		valeursPossibles.remove(el);
		
		
		System.out.println("Removing from valeursPossibles: " + el);
	}
	
	
	
	
	public String toString() {
		return "{ligne : " + ligne + ", colonne : " + colonne + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || this.getClass()!= obj.getClass()) return false;
		
		Case other = (Case) obj;
		return other.colonne == this.colonne && other.ligne == this.ligne;
	}
	
	@Override
	public int hashCode() {
		return 31*ligne + colonne;
	}
}
