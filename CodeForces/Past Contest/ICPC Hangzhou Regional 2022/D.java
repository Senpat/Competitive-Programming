//make sure to make new file!
import java.io.*;
import java.util.*;
//EXERCISE: prove this solution
public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      double[] array = new double[n];
      double sum = 0.0;
      for(int k = 0; k < n; k++){
         array[k] = Double.parseDouble(st.nextToken());
         sum += array[k];
      }
      
      double x = sum / ((double)n+1.0);
      StringJoiner sj = new StringJoiner(" ");
      sj.add("" + (2*x));
      for(int k = 1; k < n; k++) sj.add("" + x);
      
      out.println(sj.toString());
      
      
      
      
      
      out.close();
   }
   
      
}