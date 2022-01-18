//make sure to make new file!
import java.io.*;
import java.util.*;

public class EHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] rotsolves = new int[n];
         
         for(int k = 1; k <= n; k++){
            int i = k-array[k];
            if(i < 0) i += n;
            rotsolves[i]++;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int thresh = n-m*2;
         for(int k = 0; k < n; k++){
            if(rotsolves[k] >= thresh){
               //see if it works in O(N), will be done ~3 times
               
               //rotate array by k and count the number of swaps
               int[] rot = new int[n+1];
               for(int j = 1; j <= n; j++){
                  int i = j+k;
                  if(i > n) i-=n;
                  rot[j] = array[i];
               }
               
               HashSet<Integer> seen = new HashSet<Integer>();
               
               int swaps = 0;
               for(int j = 1; j <= n; j++){
                  if(seen.contains(j)) continue;
                  seen.add(j);
                  
                  int i = j;
                  while(!seen.contains(rot[i])){
                     swaps++;
                     seen.add(rot[i]);
                     i = rot[i];
                  }
               }
               
               if(swaps <= m){
                  answer.add(k);
               }
                  
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         sj.add("" + answer.size());
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}