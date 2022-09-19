//make sure to make new file!
import java.io.*;
import java.util.*;

public class B131{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         ArrayList<Integer> answer = new ArrayList<Integer>();
         HashSet<Integer> added = new HashSet<Integer>();
         int i = 1;
         while(i <= n){
            int cur = i;
            while(cur <= n){
            answer.add(cur);
            added.add(cur);
            cur*=2;
            }
            i += 2;
         }
         
         for(int k = 1; k <= n; k++){
            if(!added.contains(k)){
               answer.add(k);
            }
         }
         
         out.println(2);
         StringJoiner sj = new StringJoiner(" ");
         for(int ii : answer){
            sj.add("" + ii);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}