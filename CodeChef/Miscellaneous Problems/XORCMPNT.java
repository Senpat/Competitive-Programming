//make sure to make new file!
import java.io.*;
import java.util.*;
//xor basis practice
public class XORCMPNT{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         ArrayList<Integer> basis = new ArrayList<Integer>();
         
         st = new StringTokenizer(f.readLine());
         
         for(int k = 0; k < m; k++){
            int i = Integer.parseInt(st.nextToken());
               
            for(int b : basis){
               i = Math.min(i,i^b);
            }
            if(i > 0){
               basis.add(i);
            }
         }
         
         int answer = 1 << (n-basis.size());
         out.println(answer);
      }
      
      out.close();
   }
   
}