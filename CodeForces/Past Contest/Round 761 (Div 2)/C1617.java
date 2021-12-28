//Paprika and Permutation
import java.io.*;
import java.util.*;

public class C1617{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Integer[] array = new Integer[n];
         
         HashSet<Integer> hset = new HashSet<Integer>();
         ArrayList<Integer> notused = new ArrayList<Integer>();
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            if(array[k] > n || hset.contains(array[k])){
               notused.add(array[k]);
            } else {
               hset.add(array[k]);
            }
         }
      
         Collections.sort(notused);
         int nui = 0;
         
         boolean fail = false;
         int answer = 0;
         for(int k = 1; k <= n; k++){
            if(hset.contains(k)) continue;
            
            if((notused.get(nui)-1)/2<k){
               fail = true;
               break;
            } else {
               answer++;
            }
            
            nui++;
         }
         
         if(fail){
            out.println(-1);
         } else {
            out.println(answer);
         }
      }
      
      
      
      
      out.close();
   }
   
      
}