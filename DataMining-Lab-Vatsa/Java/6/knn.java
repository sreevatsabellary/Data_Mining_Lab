import java.util.*;
import java.io.*;

class Point{
	int x;
	int y;
	int type;
	int distance;

	Point(int x,int y,int type,int distance){
		this.x=x;
		this.y=y;
		this.type=type;
		this.distance=distance;
	}

	void calcDistance(int x1,int y1){
		int sum=(int)(Math.pow(this.x-x1,2)+Math.pow(this.y-y1,2));
		this.distance=(int)Math.sqrt(sum);
	}
	int getDistanceFromPoint(){
		return this.distance;
	}
}

public class knn{
	public static void main(String args[]){
		int n,k,x,y,type;
		int distance;
		Random r=new Random();
		ArrayList<Point>points=new ArrayList<Point>();

		Scanner s=new Scanner(System.in);
		System.out.println("\nEnter number of points for training set ");
		n=Integer.parseInt(s.next());

		for(int i=0;i<n;i++){
			x=r.nextInt(100);
			y=r.nextInt(100);
			type=r.nextInt(2);
			distance=999;
			points.add(new Point(x,y,type,distance));
		}

		System.out.println("Training data : ");
		System.out.println("x-coordinate\ty-coordinate\ttype");
		for(Point p:points){
			System.out.println(p.x+"\t\t "+p.y+"\t\t "+p.type);
		}

		Point rp=new Point(r.nextInt(100),r.nextInt(100),-1,0);
		System.out.println("Point to be classified is : (For testing)");
		System.out.println("x-coordinate\ty-coordinate");
		System.out.println(rp.x+"\t\t "+rp.y);

		for(Point p : points){
			p.calcDistance(rp.x,rp.y);
		}


		Collections.sort(points,new Comparator<Point>(){
			public int compare(Point p1,Point p2){
				return (int)Math.ceil(p1.getDistanceFromPoint()-p2.getDistanceFromPoint());
			}
		});

		Collections.sort(points,new Comparator<Point>(){
			public int compare(Point p1,Point p2){
			return (int) Math.ceil(p1.getDistanceFromPoint() - p2.getDistanceFromPoint());
			}
		});

		System.out.println("x-coordinate\ty-coordinate\ttype\tdistance from test point");
		for(Point p:points)
			System.out.println(p.x+"\t\t "+p.y+"\t\t "+p.type+"\t\t "+p.distance);

		System.out.println("Enter value of k to classify");
		k=Integer.parseInt(s.next());

		int score=0;
		for(int i=0;i<k;i++){
			if(points.get(i).type==0)
				score++;
			else
				score--;
		}

		if(score>0)
			System.out.println("Testing point is of Type 0 ");
		else if(score<0)
			System.out.println("Testing point is of Type 1 ");
		else
			System.out.println("Testing point is either of Type 0 or Type 1 ");
	}
}