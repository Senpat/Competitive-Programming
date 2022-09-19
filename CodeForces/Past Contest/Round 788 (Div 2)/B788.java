//make sure to make new file!
import java.io.*;
import java.util.*;

public class B788{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();

         HashSet<Character> hset = new HashSet<Character>();
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int ni = Integer.parseInt(st.nextToken());
         
         for(int k = 0; k < ni; k++){
            hset.add(st.nextToken().charAt(0));
         }
         
         int last = -1;
         for(int k = 0; k < n; k++){
            if(hset.contains(array[k])) last = k;
         }
         
         int cur = 1;
         int max = 0;
         for(int k = last-1; k >= 0; k--){
            if(hset.contains(array[k+1])){
               cur = 1;
            } else {
               cur++;
            }
            max = Math.max(max,cur);
         }
         
         out.println(max);
      }
      
      
      
      
      out.close();
   }
   
      
}