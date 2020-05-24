//make sure to make new file!
import java.io.*;
import java.util.*;

public class G640{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         if(n < 4){
            out.println("-1");
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         if(n%2 == 0){
            //add all evens
            for(int k = 2; k <= n; k+=2){
               answer.add(k);
            }
            answer.add(n-3);
            answer.add(n-1);
            for(int k = n-5 ;k >= 1; k-= 2){
               answer.add(k);
            }
         
         
         } else {
            for(int k = 1; k <= n; k+=2){
               answer.add(k);
            }
            answer.add(n-3);
            answer.add(n-1);
            for(int k = n-5 ;k >= 1; k-= 2){
               answer.add(k);
            }
         
         
         
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         
         out.println(sj.toString());
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}