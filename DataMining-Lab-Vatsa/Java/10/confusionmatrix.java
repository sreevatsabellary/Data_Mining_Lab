import java.util.*;
import java.io.*;

public class confusionmatrix{
	public static void main(String args[]){
		double tp,fp,tn,fn;
		Scanner s=new Scanner(System.in);

		System.out.println("Enter true positive");
		tp=Double.parseDouble(s.next());
		System.out.println("Enter true negative");
		tn=Double.parseDouble(s.next());
		System.out.println("Enter false positive");
		fp=Double.parseDouble(s.next());
		System.out.println("Enter false negative");
		fn=Double.parseDouble(s.next());

		System.out.println("------------------Confusion Matrix-------------------------");
		System.out.println("\t\tPredicted(No)\t\tPredicted(Yes)");
		System.out.println("Actual(No)\t  "+tn+"(TN)\t\t  "+fp+"(FP)");
		System.out.println("Actual(Yes)\t  "+fn+"(FN)\t\t  "+tp+"(TP)");
		System.out.println("");

		double sensitivity=tp/(tp+fn);
		double specificity=tn/(tn+fp);
		double precision=tp/(tp+tn);
		double recall=sensitivity;
		double accuracy=(tp+tn)/(tp+tn+fp+fn);

		System.out.println("Sensitivity is : "+sensitivity);
		System.out.println("Specificity is : "+specificity);
		System.out.println("Recall is : "+recall);
		System.out.println("Precesion is : "+precision);
		System.out.println("Accuracy is : "+accuracy);
	}
}