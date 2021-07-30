package snakeAndLadder;

public class LadderIdGenerator {

	private static Integer id=0;
	
	public static String getId() {
		return String.valueOf(id++);
	}
}
