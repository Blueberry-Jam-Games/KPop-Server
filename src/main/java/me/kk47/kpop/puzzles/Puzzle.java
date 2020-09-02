package me.kk47.kpop.puzzles;

public class Puzzle {

	private final int index;
	private int same;
	private int differences;
	
	public Puzzle(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	public String toFormat() {
		return "id=" + index + ",im1=" + index + "a.png" + "," + "im2=" + index + "b.png";
	}
	
	public void addSame() {
		same++;
	}
	
	public void addDifference() {
		differences++;
	}
	
	public String toTableRow() {
		return "<tr><td>" + index + "</td><td>" + same + "</td><td>" + differences + "</td></tr>";
	}
}
