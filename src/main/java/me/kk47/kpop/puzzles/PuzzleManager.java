package me.kk47.kpop.puzzles;

import java.util.ArrayList;
import java.util.List;

public class PuzzleManager {

	public static List<Puzzle> puzzles = new ArrayList<Puzzle>();
	static int userPuzzle = 0;
	
	public static void registerValidPuzzle(int index) {
		puzzles.add(new Puzzle(index));
	}
	
	public static int getNextPuzzleID() {
		return puzzles.size(); // Because 0 indexing, this works
	}
	
	public static Puzzle getNextPuzzle() {
		System.out.println("Get next puzzle");
		userPuzzle++;
		System.out.println("Index = " + userPuzzle);
		if(userPuzzle >= puzzles.size()) {
			userPuzzle = 0;
		}
		System.out.println("Index now " + userPuzzle + " and size " + puzzles.size());
		return puzzles.get(userPuzzle);
	}
	
	public static void recordAnswer(int puzzle, boolean same) {
		Puzzle pzl = puzzles.get(puzzle);
		if(same) {
			pzl.addSame();
		} else {
			pzl.addDifference();
		}
	}
}
