package core;

public class Grass extends AbstractBlockRoadObject {

	public Grass() {
		this.createsRoad();
	}
	public void createsRoad(){
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				matrix [j] [i]=1;
			}
		}
	}
}