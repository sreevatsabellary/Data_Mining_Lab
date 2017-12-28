import java.util.*;
import java.io.*;

public class gain{

	static int no_of_col=4;
	static ArrayList<String[]>arr=new ArrayList<>();
	static HashMap<String,ArrayList<String[]>>hm=new HashMap<String,ArrayList<String[]>>();
	static Double calcGini(int i){
		hm.clear();
		for(String[] s:arr){
			String key=s[i];
			if(!hm.containsKey(key)){
				ArrayList<String[]>a=new ArrayList<>();
				a.add(s);
				hm.put(key,a);
			}else{
				ArrayList<String[]>a=hm.get(key);
				a.add(s);
				hm.put(key,a);
			}
		}

		ArrayList<Double>nodeGinivalues=new ArrayList<>();
		for(String key:hm.keySet()){
			ArrayList<String[]> currentNode=hm.get(key);
			double numOfYes=0,numOfNo=0;
			for(String[] str:currentNode){
				if(str[no_of_col-1].equals("Yes"))
					numOfYes++;
				else
					numOfNo++;
			}
			double size=currentNode.size();
			double giniOfNode=1-Math.pow((numOfYes/size),2)-Math.pow((numOfNo/size),2);
			giniOfNode=giniOfNode*size;
			nodeGinivalues.add(giniOfNode);
		}
		double weightedGiniofAttribute=0;
		for(Double d:nodeGinivalues)
			weightedGiniofAttribute+=d/arr.size();
		return weightedGiniofAttribute;
	}
	public static void main(String[] args) throws IOException,FileNotFoundException{

		double gini[]=new double[3];
		double gain[]=new double[3];	
		double parentgini=0;	
		BufferedReader br=new BufferedReader(new FileReader(new File("data.csv")));
		String line;
		while((line=br.readLine())!=null){
			String singleLine[]=line.split(",");
			arr.add(singleLine);
		}

		double countyes=0,countno=0;
		for(String[]s:arr){
			if(s[no_of_col-1].equals("Yes"))
				countyes++;
			else
				countno++;
		}

		double sz=arr.size();
		parentgini=1-(countyes*countyes)/(sz*sz)-(countno*countno)/(sz*sz);
		System.out.println("Parent gini : "+parentgini);

		for(int i=0;i<no_of_col-1;i++){
			gini[i]=calcGini(i);
			gain[i]=parentgini-gini[i];
		}

		System.out.println("Correspoing measures for different attributes are");
		for(int i=0;i<no_of_col-1;i++){
			System.out.println("Gini of attribute "+i+" is : "+gini[i]);
			System.out.println("Gain of attribute "+i+" is : "+gain[i]);
		}

		double max=Integer.MIN_VALUE;
		int temp=-1;
		for(int i=0;i<no_of_col-1;i++){
			if(gain[i]>max){
				max=gain[i];
				temp=i;
			}
		}
		System.out.println("Max gain is "+max+" for attribute(coloumn) "+temp);
		System.out.println("Best Split is attribute(coloumn) : "+temp);
	}
}