//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static int[] pow;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         int b = Integer.parseInt(f.readLine());
         int x = Integer.parseInt(f.readLine())-1;
      
         if(x < b){
            out.println(x+10-b);
            continue;
         }
      
         pow = new int[100];
         pow[0] = 1;
         for(int k = 1; k < 100; k++){
            pow[k] = pow[k-1]*b;
            if(pow[k] > 1000000000) break;
         }
         
         int under = 0;
         int max = b;
         int p = 1;
         
         while(max <= x && max >= 0){
            under += pow[p];
            max += pow[p+1];
            p++;
         }
         
         x -= under;
         ArrayList<Integer> answer = new ArrayList<Integer>();
         for(int k = p-1; k >= 0; k--){
            answer.add(x / pow[k]);
            x %= pow[k];
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int i : answer){
            sj.add("" + (i+10-b));
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}