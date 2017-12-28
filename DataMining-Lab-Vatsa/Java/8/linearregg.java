import java.util.*;
import java.io.*;

public class linearregg{

		static double M=0;
		static double C=0;

		static double mean(double [] data){
			double sum=0;
			for(double d:data)
				sum+=d;
			return sum/data.length;
		}

		static double variance(double [] data){
			double meanx=mean(data);
			double sum=0;
			for(double d:data)
				sum+=Math.pow((meanx-d),2);
			return sum/data.length;
		}

		static double covariance(double [] datax,double [] datay){
			double meanx=mean(datax);
			System.out.println("Meanx "+ meanx);
			double meany=mean(datay);
			System.out.println("Meany "+ meany);
			double sum=0;

			for(int i=0;i<datax.length;i++)
				sum+=(datax[i]-meanx)*(datay[i]-meany);

			return sum/datax.length;
		}

	public static void main(String args[]){

        double[] datax = { 1,2,3,4,5};
        double[] datay = { 5,8,11,14,17};
		double x,y;
		Scanner s=new Scanner(System.in);

		M=covariance(datax,datay)/variance(datax);
		C=mean(datay)-M*mean(datax);
		System.out.println("Variance "+ variance(datax));
		System.out.println("Covariance "+ covariance(datax,datay));

		//System.out.println(M+"\n"+C);

		System.out.println("Enter value of x coordinate : ");
		x=Double.parseDouble(s.next());

		y=M*x+C;
		System.out.println("y-coordinate is : "+y);
	}
}