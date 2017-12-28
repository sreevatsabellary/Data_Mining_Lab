import java.util.*;
import java.io.*;

public class replace{
	public static void main(String args[]) throws IOException,FileNotFoundException{
		String filename;
		ArrayList<String[]>records=new ArrayList<>();
		String line;
		Scanner st=new Scanner(System.in);

		System.out.println("Enter file name");
		filename=st.next();

		BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		while((line=br.readLine())!=null){
			String singleLine[]=line.split(",");
			records.add(singleLine);
		}

		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		int sum1=0,sum2=0;
		float avg1,avg2;

		for(String[] s: records){
			if(!s[0].equals(" "))
				sum1+=Integer.parseInt(s[0]);
			if(!s[1].equals(" ")){
				if(!hm.containsKey(s[1]))
					hm.put(s[1],1);
				else{
					int count=hm.get(s[1]);
					count++;
					hm.put(s[1],count);				
				}
			}
			if(!s[2].equals(" "))
				sum2+=Integer.parseInt(s[2]);
		}
		avg1=sum1/records.size();
		avg2=sum2/records.size();

		int max=Integer.MIN_VALUE;
		String replaceString="";
		for(String key:hm.keySet())
			if(hm.get(key)>max){
				replaceString=key;
				max=hm.get(key);
			}

		for(String[] s:records){
			if(s[0].equals(" "))
				s[0]=Float.toString(avg1);
			if(s[1].equals(" "))
				s[1]=replaceString;
			if(s[2].equals(" "))
				s[2]=Float.toString(avg2);			
		}

		System.out.println("File after replacing values is ");
		for(String[] s:records)
			System.out.println(s[0]+"\t"+s[1]+"\t"+s[2]);
	}
}