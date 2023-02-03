//make sure to make new file!
import java.io.*;
import java.util.*;

public class D140{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 18;
      int[] pow2 = new int[N+1];
      pow2[0] = 1;
      for(int k = 1; k <= N; k++){
         pow2[k] = 2*pow2[k-1];
      }
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      int[] array = new int[n];
      int x0 = 0;
      int x1 = 0;
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(s.charAt(k));
         if(array[k] == 0) x0++;
         if(array[k] == 1) x1++;
      }
      
      int l = 0;
      int r = 0;
      
      for(int k = 0; k < x0; k++){
         r += pow2[k];
      }
      
      for(int k = 0; k < x1; k++){
         l += pow2[k];
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = l+1; k <= pow2[n]-r; k++){
         sj.add("" + k);
      }
      out.println(sj.toString());
      
      
      
      
      
      out.close();
   }
   
      
}