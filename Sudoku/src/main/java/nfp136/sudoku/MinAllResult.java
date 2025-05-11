package nfp136.sudoku;

import java.util.Objects;

public class MinAllResult {
	int index;
	String type;
	
	public MinAllResult(int index, String type) {
		this.type = type;
		this.index = index;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) return true;
		if(!(o instanceof MinAllResult)) return false;
		
		MinAllResult that = (MinAllResult) o;
		
		return ((this.index == that.index) && this.type.equals(that.type));
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(index, type);
	}
}
