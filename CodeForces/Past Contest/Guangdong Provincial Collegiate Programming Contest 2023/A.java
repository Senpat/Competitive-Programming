//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int y1 = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            hset.add(Integer.parseInt(st.nextToken()));
         }
         
         int y2 = Integer.parseInt(f.readLine());
         
         int answer = 0;
         for(int k = y1; k <= y2; k++){
            if(!hset.contains(k)) answer++;
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}