import java.util.*;
import java.io.*;

public class perceptron{
	public static void main(String[] args) {
		double bias=0.4,learningrate=0.5;
		Scanner s=new Scanner(System.in);
		int n;

		System.out.println("Enter number of records");
		n=Integer.parseInt(s.next());

		int x1,x2,x3,score=0;
		Random r=new Random();
		int td[][]=new int[n][4];

		for(int i=0;i<n;i++){
			int y;
			score =0;
			x1=r.nextInt(2);
			x2=r.nextInt(2);
			x3=r.nextInt(2);

			if(x1>0)
				score++;
			if(x2>0)
				score++;
			if(x3>0)
				score++;

			if(score>1)
				y=1;
			else
				y=-1;

			td[i][0]=x1;
			td[i][1]=x2;
			td[i][2]=x3;
			td[i][3]=y;
		}

		System.out.println("DataSet is \n");
		System.out.println("x1\tx2\tx3\ty");
		for(int i=0;i<n;i++)
			System.out.println(td[i][0]+"\t"+td[i][1]+"\t"+td[i][2]+"\t"+td[i][3]);
		System.out.println("");

		double totalerror=-1;
		double weights[]=new double[3];
		int pred[]=new int[n];
		weights[0]=r.nextDouble();
		weights[1]=r.nextDouble();
		weights[2]=r.nextDouble();


		System.out.println("\nInitial values of weights are : ");
		System.out.println("Weight[0] = "+weights[0]);
		System.out.println("Weight[1] = "+weights[1]);
		System.out.println("Weight[2] = "+weights[2]);

		int g=0;
		while(totalerror!=0.0 && g<5){
			totalerror=0;
			for(int i=0;i<n;i++){
				double a=weights[0]*td[i][0]+weights[1]*td[i][1]+weights[2]*td[i][2]-bias;
				int y1;
				if(a>0)
					y1=1;
				else
					y1=-1;
				pred[i]=y1;
				int y=td[i][3];
				totalerror+=Math.abs(y-y1);
				for(int j=0;j<3;j++)
					weights[j]=weights[j]+learningrate*(y-y1)*td[i][j];
			}
			System.out.println("Predicted values of y correspondingly are");
			System.out.println("");
			for(int i:pred)
				System.out.print(i+"\t");
			System.out.println("");
			System.out.println("Error rate : "+totalerror);
			if(totalerror==0)
				System.out.println("Reached optimal values of weights ");
			else
				System.out.println("Updation in weights required.. Updating!!");
			g++;		
		}
		System.out.println("Updated values of weights are : ");
		System.out.println("Weight[0] = "+weights[0]);
		System.out.println("Weight[1] = "+weights[1]);
		System.out.println("Weight[2] = "+weights[2]);
		System.out.println("Final Error rate : "+totalerror);
	}
}