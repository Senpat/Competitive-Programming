//make sure to make new file!
import java.io.*;
import java.util.*;

public class D598{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         int[] array = new int[n];
         
         String s = f.readLine();
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         //number of zeros at the front
         int numzeros = 0;
         for(int k = 0 ; k < n; k++){
            if(array[k] == 0) numzeros++;
            else break;
         }
         
         for(int k = numzeros; k < n && m > 0; k++){
            if(array[k] != 0) continue;
            if(k-numzeros <= m){
               m -= (long)(k-numzeros);
               array[k] = 1;
               array[numzeros] = 0;
               numzeros++;
            } else {
               array[(int)(k-m)] = 0;
               array[k] = 1;
               break;
            }
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sj.add("" + array[k]);
         }
         
         out.println(sj);
         
      
      }
      
      
      
      
      out.close();
   }
   
      
}