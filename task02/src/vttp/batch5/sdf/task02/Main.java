package vttp.batch5.sdf.task02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws MyException, IOException{

		String fileName = "";

		if(args.length > 0){

			fileName = args[0];

		} else {

			fileName = "";
		}
		


		File file = new File(fileName);

		Board newGame = new Board(file);

		System.out.println(newGame.toPrintBoard());

		// newGame.minimax(newGame.getBoard(), true);

		newGame.possibleMoves(newGame.getBoard());

		String ans = toStringAll(newGame.getMovesMade(), newGame.getMoveValues());
		System.out.println(ans);
		// System.out.println(newGame.getMoveValues().toString());

		

	
	}



	public static String toStringAll(ArrayList<MoveInfo> array, ArrayList<Integer> intArray){

		String result = "";
		int i = 0;

		for(MoveInfo m : array){

			result+= "y=" + m.getCol() + ", x=" + m.getRow() + ", " + "utility=" + intArray.get(i) + "\n";
			i++;
		}
		

		return result;

	}

}


