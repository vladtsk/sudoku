package nfp136.sudoku;

import java.io.*;

public class SudokuFileReader {

    public static int[][] loadGrid(String filename) throws IOException {
        int[][] grid = new int[9][9];
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null && rowCount < 9) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;
                if (line.length() < 9) throw new IOException("Line too short: " + line);

                for (int col = 0; col < 9; col++) {
                    char c = line.charAt(col);
                    grid[rowCount][col] = (c == '.') ? 0 : Character.getNumericValue(c);
                }

                rowCount++;
            }

            if (rowCount < 9) throw new IOException("Le nombre de lignes est insuffisant.");
        }

        return grid;
    }

    
    public static void main(String[] args) throws IOException {
        int[][] grid = loadGrid("grille1.txt");

        // print the grid
        for (int i = 0; i < 9; i++) {
            System.out.print("int[] row" + (i + 1) + " = {");
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j]);
                if (j < 8) System.out.print(", ");
            }
            System.out.println("};");
        }

     
 
   
    }
}

