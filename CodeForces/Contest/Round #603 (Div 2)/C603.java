//make sure to make new file!
import java.io.*;
import java.util.*;

public class C603{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int i = 1;
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         while(i <= n){
            int a = n/i;
            i = n/a+1;
            answer.add(a);
         
         }
         
         answer.add(0);
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int k = answer.size()-1; k >= 0; k--){
            sj.add("" + answer.get(k));
         }
         out.println(sj);

      }
      
      
      
      
      out.close();
   }
   
      
}