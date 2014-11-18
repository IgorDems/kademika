package TanksObj;
//import TanksObj.BattleFieldTemplate2;
public class Bullet {

	int speed=5;
	int x;// = tankX + 25;
	int y;// = tankY + 25;
	int direction;
	
	public Bullet(int x,int y,int direction ){
		this.direction=direction;
		this.x=x;
		this.y=y;
	}
	
	public void updateX(int x){
		x+=x;
	}
	public void updateY(int y){
		y+=y;
	}
	public void destroy(){
		x=-100;
		y=-100;
	}
	public int getSpeed(){
		return speed;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getDirection(){
		return direction;
	}
}
