import java.util.*;
import java.io.*;

public class ads{

	public static void main(String srgs[]) throws IOException,FileNotFoundException{
		String filename;
		ArrayList<String[]> records=new ArrayList<>();
		Scanner st=new Scanner(System.in);

		System.out.println("Enter file name : ");
		filename=st.next();

		BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		String line;
		while((line=br.readLine())!=null){
			String [] singleLine=line.split(",");
			records.add(singleLine);
		}

		System.out.println("Performing Aggregation ");
		int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE,sum=0;
		float avg=0;
		for(String[] s: records){
			if(Integer.parseInt(s[1])<min)
				min=Integer.parseInt(s[1]);
			if(Integer.parseInt(s[1])>max)
				max=Integer.parseInt(s[1]);
			sum+=Integer.parseInt(s[1]);
		}
		avg=sum/records.size();
		System.out.println("Min is : "+min);
		System.out.println("MAx is : "+max);
		System.out.println("Avg is : "+avg);

		System.out.println("Performing Discretisation ");
		int mean=(min +max)/2;
		int mid1=(min +mean)/2;
		int mid2=(mean +max)/2;

		HashMap<String,String>hm=new HashMap<String,String>();
		for(String [] s:records){
			String temp;
			if(Integer.parseInt(s[1])>=min && Integer.parseInt(s[1])<mid1){
				temp="["+min+"-"+mid1+")";
				hm.put(temp,s[2]);
				System.out.println(temp+"\t"+s[1]+"\t"+s[2]);
			}
			else if(Integer.parseInt(s[1])>=mid1 &&Integer.parseInt(s[1])<mean){
				temp="["+mid1+"-"+mean+")";
				hm.put(temp,s[2]);
				System.out.println(temp+"\t"+s[1]+"\t"+s[2]);
			}
			else if(Integer.parseInt(s[1])>=mean && Integer.parseInt(s[1])<mid2){
				temp="["+mean+"-"+mid2+")";
				hm.put(temp,s[2]);
				System.out.println(temp+"\t"+s[1]+"\t"+s[2]);
			}
			else{
				temp="["+mid2+"-"+max+")";
				hm.put(temp,s[2]);
				System.out.println(temp+"\t"+s[1]+"\t"+s[2]);				
			}
		}

		System.out.println("Sampling for the generated discretized model ");
		for(String key:hm.keySet()){
			System.out.println(key+"\t"+hm.get(key));
		}
	}
}