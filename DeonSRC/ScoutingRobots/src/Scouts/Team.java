package Scouts;

public class Team {
	private int TeamNumber;
	private int CrossedPortcullis;
	private int CrossedCheval;
	private int CrossedRamparts;
	private int CrossedMoat;
	private int CrossedDraw;
	private int CrossedSally;
	private int CrossedRock;
	private int CrossedRough;
	public Team(int TNum) {
		TeamNumber=TNum;
		CrossedPortcullis=0;
		CrossedCheval=0;
		CrossedRamparts=0;
		CrossedMoat=0;
		CrossedDraw=0;
		CrossedSally=0;
		CrossedRock=0;
		CrossedRough=0;
	}
	//set functions essentially
	public void addToPort(int times){
		CrossedPortcullis= CrossedPortcullis + times;
	}
	public void addToCheval(int times){
		CrossedCheval= CrossedCheval + times;
	}
	public void addToRamparts(int times){
		CrossedRamparts= CrossedRamparts + times;
	}
	public void addToMoat(int times){
		CrossedMoat= CrossedMoat + times;
	}
	public void addToDraw(int times){
		CrossedDraw= CrossedDraw + times;
	}
	public void addToSally(int times){
		CrossedSally= CrossedSally + times;
	}
	public void addToRock(int times){
		CrossedRock= CrossedRock + times;
	}
	public void addToRough(int times){
		CrossedRough= CrossedRough + times;
	}
	//Get function
	public int getPort(){
		return CrossedPortcullis;
	}
	public int getCheval(){
		return CrossedCheval;
	}
	public int getRamparts(){
		return CrossedRamparts;
	}
	public int getMoat(){
		return CrossedMoat;
	}
	public int getDraw(){
		return CrossedDraw;
	}
	public int getSally(){
		return CrossedSally;
	}
	public int getRock(){
		return CrossedRock;
	}
	public int getRough(){
		return CrossedRough;
	}
}
