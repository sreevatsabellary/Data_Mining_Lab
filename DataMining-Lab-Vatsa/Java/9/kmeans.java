import java.util.*;
import java.io.*;

class Record{
	double x;
	double y;
	int cluster;
	Record(double x,double y,int z){
		this.x=x;
		this.y=y;
		cluster=z;
	}

	double calculateDistance(double cx,double cy){
		double sum=Math.pow(this.x-cx,2)+Math.pow(this.y-cy,2);
		return Math.sqrt(sum);
	}
}

public class kmeans{

	static ArrayList<Record> records=new ArrayList<>();

	public static void main(String args[]) throws IOException,FileNotFoundException{
		int k;
		Scanner st=new Scanner(System.in);
		System.out.println("Enter file name : ");
		String filename=st.next();
		
        BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		String line;
		while((line=br.readLine())!=null){
			String singleLine[]=line.split(",");
			records.add(new Record(Double.parseDouble(singleLine[0]),Double.parseDouble(singleLine[1]),-1));
		}

		System.out.println("Enter value of K : ");
		k=Integer.parseInt(st.next());

		Record centroid[]=new Record[k];
		Random rd=new Random();
		for(int i=0;i<k;i++)
			centroid[i]=new Record(rd.nextInt(11),rd.nextInt(11),i);

		int numOfChanges=-1;
		int g=0;
		double meanx[]=new double[k];
		double meany[]=new double[k];
		double num[]=new double[k];

		while(numOfChanges!=0 && g<10){
			numOfChanges=0;
			System.out.println("Centroid values are : ");
			for(int i=0;i<k;i++){
				System.out.println("Centroid of Cluster ["+i+"] is : "+centroid[i].x+"  "+centroid[i].y);
			}
			for(Record r: records){
				double d[]=new double[k];
				int min=Integer.MAX_VALUE;
				int temp=-1;
				for(int i=0;i<k;i++)
					d[i]=r.calculateDistance(centroid[i].x,centroid[i].y);	
				for(int i=0;i<k;i++){
					if((int)d[i]<min){
						min=(int)d[i];
						temp=i;
					}
				}
				r.cluster=temp;	
 			}

 			Arrays.fill(meanx,0.0);
 			Arrays.fill(meany,0.0);
 			Arrays.fill(num,0.0);

 			for(int i=0;i<records.size();i++){
 				Record r=records.get(i);
 				meanx[r.cluster]+=r.x;
 				meany[r.cluster]+=r.y;
 				num[r.cluster]++;
 			}
 			for(int i=0;i<k;i++){
 				meanx[i]=meanx[i]/num[i];
 				meany[i]=meany[i]/num[i];
 				if(meanx[i]!=centroid[i].x || meany[i]!=centroid[i].y)
 					numOfChanges++;
 				centroid[i].x=meanx[i];
 				centroid[i].y=meany[i];
 			}
 			if(numOfChanges==0)
 				System.out.println("Got the optimal centroid values : ");
 			if(g==9)
 				System.out.println("Got the approximate centroid values after many iterations : ");
			g++;
		}
		System.out.println("Centroid values are : ");
		for(int i=0;i<k;i++){
			System.out.println("Centroid of Cluster ["+i+"] is : "+centroid[i].x+"  "+centroid[i].y);
		}
		System.out.println("Records with their respective clusters are : ");
		for(int i=0;i<k;i++){
			System.out.println("-----------------Cluster["+i+"]-------------------------");
			System.out.println("x\ty\tcluster");
			for(Record r:records)
				if(r.cluster==i)
					System.out.println(r.x+"\t"+r.y+"\t  "+r.cluster);
		}
	}
}