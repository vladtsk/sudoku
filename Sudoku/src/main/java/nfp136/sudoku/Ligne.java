package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Ligne {
	static int ligneCompteur = 0;
	int numLigneCourante;
	
	private int[] ligne;
	int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	ArrayList<Integer> elementsManquants = new ArrayList<>();
	ArrayList<Case> casesVides = new ArrayList<>();
	
	private Grille parentGrille;

	public Ligne(int[] ligne, Grille parentGrille) {
		this.ligne = ligne;
		this.parentGrille = parentGrille;
		ligneCompteur ++;
		numLigneCourante = ligneCompteur;
		//System.out.println("numLigneCourante");
		//System.out.println(numLigneCourante);
		
		for(int num : nums) {
			boolean found = false;
			for(int el : ligne) {
				if(el == num) {
					found = true;
					break;
				}
			}
			if(found == false) {
				elementsManquants.add(num);
			}
			
		} 
	}
	
	public Ligne(int[] ligne) {
		this(ligne, null);
	}
	
	public int[] getLigne() {
		return ligne;
	}
	
	public int calculerNbElements() {
		int nb_el = 0;
		for(int el : ligne) {
			if(el != 0) {
				nb_el ++;
			}
		}
		return nb_el;
	}
	
	
	public ArrayList<Integer> getElementsManquants() {
		elementsManquants.clear();
		for(int num : nums) {
			boolean found = false;
			for(int el : ligne) {
				if(el == num) {
					found = true;
					break;
				}
			}
			if(found == false) {
				elementsManquants.add(num);
			}
			
		} 
		return elementsManquants;
	}
	
	public ArrayList<Case> getCasesVides() {
		casesVides.clear();
		int i;
		for(i = 0; i < ligne.length; i++) {
			if(ligne[i] == 0) {
				casesVides.add(new Case(numLigneCourante, i+1));
			}
		}
		
		return casesVides;
	}
	
	public boolean contientElement(int el) {
		boolean contient = false;
		for(int element : ligne) {
			if(element == el) contient = true;
		}
		return contient;
	}
	
	public void updateLigne(int col, int el) {
		ligne[col-1] = el;
		System.out.println("Updating ligne " + numLigneCourante + ": " + "with el " + el);
		
		//parentGrille.removeCasesDeuxEl(numLigneCourante, col);
		
	}
	
	
	
	@Override
	public String toString() {
		return "ligne : " + Arrays.toString(ligne);
	}
}
