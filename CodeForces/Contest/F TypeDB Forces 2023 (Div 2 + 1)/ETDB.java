//make sure to make new file!
import java.io.*;
import java.util.*;

public class ETDB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         //xor all
         int allxor = 0;
         for(int k = 1; k <= n; k++){
            allxor ^= k;
         }
         
         if(m % 2 == 0 && allxor != 0){
            out.println("NO");
            continue;
         }
         
         if(m % 2 == 1 && allxor != x){
            out.println("NO");
            continue;
         }
         
         ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>(m);
         for(int k = 0; k < m; k++) answer.add(new ArrayList<Integer>());
         
         int added = 0;
         HashSet<Integer> used = new HashSet<Integer>();
         for(int k = 1; k <= n; k++){
            if(used.contains(k)) continue;
            
            if(k == x){
               used.add(k);
               answer.get(added).add(k);
               added++;
            } else {
            
               if(used.contains(k^x) || (k^x) > n) continue;
               
               used.add(k);
               used.add(k^x);
               answer.get(added).add(k);
               answer.get(added).add(k^x);
               added++;
            }
            
            if(added >= m) break;
         }
         
         //see if the rest of the numbers xor to 0
         int restxor = 0;
         for(int k = 1; k <= n; k++){
            if(used.contains(k)) continue;
            restxor ^= k;
            answer.get(0).add(k);
            added = Math.max(1,added);
         }
         
         if(added == m){
            StringJoiner sj1 = new StringJoiner("\n");
            sj1.add("YES");
            for(int k = 0; k < m; k++){
               StringJoiner sj2 = new StringJoiner(" ");
               sj2.add("" + answer.get(k).size());
               for(int i : answer.get(k)){
                  sj2.add("" + i);
               }
               sj1.add(sj2.toString());
            }
            out.println(sj1.toString());
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}