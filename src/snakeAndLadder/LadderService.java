package snakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class LadderService {

	private static List<Ladder> allLadders=null;
	
	private static LadderService ladderService=null;
	
	private LadderService() {
		
	}
	
	public static LadderService getInstance() {
		if(ladderService==null) {
			allLadders=new ArrayList<>();
			ladderService=new LadderService();
		}
		return ladderService;
	}
	
	public void addLadder(Integer startPos,Integer endPos) throws InvalidLadderException {
		if(startPos>=endPos) {
			throw new InvalidLadderException("Start of ladder cannot be above end of ladder");
		}
		Ladder ladder=new Ladder();
		ladder.setLadderId(LadderIdGenerator.getId());
		ladder.setStartPos(startPos);
		ladder.setEndPos(endPos);
		allLadders.add(ladder);
	}
	
	public Integer findLadderAtPos(Integer startPos) {
		int endPos=-1;
		for(Ladder ladder:allLadders) {
			if(ladder.getStartPos()==startPos) {
				endPos=ladder.getEndPos();
				break;
			}
		}
		return endPos;
	}
}
