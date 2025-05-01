package nfp136.sudoku;

public class Element {
	
	int ligne;
	int colonne;
	int sous_carre;
	int value;
	
	public Element(int ligne, int colonne, int sous_carre) {
		this.ligne= ligne;
		this.colonne = colonne;
		this.sous_carre = sous_carre;
	}
}
