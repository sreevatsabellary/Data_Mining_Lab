 //(1 / (sqrt(2 * PI) * sd)) * exp(-((x-mean^2)/(2*sd^2)))

import java.util.*;
import java.io.*;

class Record{
	Double attr1;
	String attr2;
	int attr3;

	Record(Double x,String y,int z){
		attr1=x;
		attr2=y;
		attr3=z;
	}
}

public class NaiveBaise{
	static double mean0,mean1;
	static double var0,var1;
	static double probS0,probS1,probM0,probM1,probL0,probL1;
	public static void main(String[] args) throws FileNotFoundException,IOException{
		String filename;
		Scanner st=new Scanner(System.in);
		System.out.println("Enter file name ");
		filename=st.next();

		ArrayList<Record>records=new ArrayList<>();
		BufferedReader br=new BufferedReader(new FileReader(new File(filename)));	
		String line;

		while((line=br.readLine())!=null){
			String singleLine[]=line.split(",");
			records.add(new Record(Double.parseDouble(singleLine[0]),singleLine[1],Integer.parseInt(singleLine[2])));
		}	

		int sum0=0,sum1=0;
		int count0=0,count1=0;
		for(Record r: records){
			if(r.attr3==0){
				sum0+=r.attr1;
				count0++;
			}
			else{
				sum1+=r.attr1;
				count1++;
			}
		}
		mean0=sum0/count0;
		mean1=sum1/count1;

		var0=0;
		var1=0;
		for(Record r:records){
			if(r.attr3==0){
				var0+=Math.pow(mean0-r.attr1,2);
			}
			else{
				var1+=Math.pow(mean1-r.attr1,2);
			}		
		}
		var0=var0/count0;
		var1=var1/count1;

		int s0=0,s1=0,m0=0,m1=0,l0=0,l1=0;
		for(Record r:records){
			if(r.attr2.equals("S") && r.attr3==0)
				s0++;
			if(r.attr2.equals("S") && r.attr3==1)
				s1++;
			if(r.attr2.equals("M") && r.attr3==0)
				m0++;
			if(r.attr2.equals("M") && r.attr3==1)
				m1++;
			if(r.attr2.equals("L") && r.attr3==0)
				l0++;
			if(r.attr2.equals("L") && r.attr3==1)
				l1++;
		}
		probS0=(double)s0/count0;
		probS1=(double)s1/count1;
		probM0=(double)m0/count0;
		probM1=(double)m1/count1;
		probL0=(double)l0/count0;
		probL1=(double)l1/count1;


		System.out.println("Enter the values of test attribute to predict(First numerical then categorial)");
		Double newattr1=Double.parseDouble(st.next());
		String newattr2=st.next();

		double probcat0=0,probcat1=0;
		switch(newattr2){
			case "S" : probcat0=probS0;
					   probcat1=probS1;
					   break;
			case "M" : probcat0=probM0;
					   probcat1=probM1;
					   break;
			case "L" : probcat0=probL0;
					   probcat1=probL1;
					   break;
		}

		double guass0=(Math.exp(-((newattr1-mean0)*(newattr1-mean0)/(2*var0)))/Math.sqrt(2*3.14*var0));
		double guass1=(Math.exp(-((newattr1-mean1)*(newattr1-mean1)/(2*var1)))/Math.sqrt(2*3.14*var1));

		double finalprob0=guass0*probcat0;
		double finalprob1=guass1*probcat1;

		/*System.out.println("Mean0 "+mean0);
		System.out.println("Mean1 "+mean1);
		System.out.println("Var0 "+var0);
		System.out.println("Var1 "+var1);
		System.out.println("Guass0 "+guass0);
		System.out.println("Guass1 "+guass1);
		System.out.println("Probcat0 "+probcat0);
		System.out.println("Probcat1 "+probcat1);*/

		System.out.println("Probability of class 0 for test record is : "+finalprob0);
		System.out.println("Probability of class 1 for test record is : "+finalprob1);

		if(finalprob0>finalprob1)
			System.out.println("New record belongs to class 0");
		else
			System.out.println("New record belongs to class 1");
	}
}