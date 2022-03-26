//make sure to make new file!
import java.io.*;
import java.util.*;

public class BTON1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[] array = new int[n];
         HashSet<Integer> hset = new HashSet<Integer>();
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            hset.add(array[k]);
         }
         
         boolean found = false;
         for(int k = 0; k < n; k++){
            if(hset.contains(array[k]-m) || hset.contains(array[k]+m)){
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}