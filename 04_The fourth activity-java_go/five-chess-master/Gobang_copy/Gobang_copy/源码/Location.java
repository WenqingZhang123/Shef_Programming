
public class Location{

	private int x;//ĳ������λ�ú����꣬0-14
	private int y;//ĳ������λ�������꣬0-14
	
	private int owner;//ռ�ݸ�λ�õ����ַ���1�����࣬-1�ǻ�����0�ǿ�
	private int score;//�Ը�λ�õĴ�ķ���

	public Location(){}
	public Location(int x, int y, int owner){
		this.x = x;
		this.y = y;
		this.owner = owner;
	}
	public Location(int x, int y, int owner, int score){
		this(x, y, owner);
		this.score = score;
	}

	public int getX(){return this.x;}
	public void setX(int x){this.x = x;}
	
	public int getY(){return this.y;} 
	public void setY(int y){this.y = y;}

	public int getOwner(){return this.owner;}
	public void setOwner(int owner){this.owner = owner;}

	public int getScore(){return this.score;}
	public void setScore(int score){this.score = score;}
	

}