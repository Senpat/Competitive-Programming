//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1295{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int[] dif = new int[n];
         
         if(array[0] == '1') dif[0] = -1;
         else dif[0] = 1;
         
         HashSet<Integer> difs = new HashSet<Integer>();
         difs.add(dif[0]);
         for(int k = 1; k < n; k++){
            if(array[k] == '1') dif[k] = dif[k-1]-1;
            else dif[k] = dif[k-1]+1;
            difs.add(dif[k]);
         }
         
         if(dif[n-1] == 0){
            if(difs.contains(m)){
               out.println(-1);
            } else {
               out.println(0);
            }
         } else {
         
            int answer = 0;
            
            if(m == 0) answer++;
               
            if(dif[n-1] > 0){
               for(int k = 0; k < n; k++){
                  if(m >= dif[k] && (m-dif[k])%dif[n-1] == 0) answer++;
               }
            } else {
               for(int k = 0; k < n; k++){
                  if(m <= dif[k] && (dif[k]-m)%(dif[n-1]*-1) == 0) answer++;
               }
            }
            out.println(answer);
         }
            
      
      }
      
      
      
      
      out.close();
   }
   
      
}