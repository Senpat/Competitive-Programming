//make sure to make new file!
import java.io.*;
import java.util.*;

public class E910{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] s1 = f.readLine().toCharArray();
         char[] s2 = f.readLine().toCharArray();
         
         ArrayList<Queue<Integer>> qs = new ArrayList<>(26);
         for(int k = 0; k < 26; k++){
            qs.add(new LinkedList<Integer>());
         }
         
         for(int k = 0; k < n; k++){
            qs.get(s1[k]-'a').add(k);
         }
         
         boolean fail = false;
         for(int k = 0; k < m; k++){
            int ci = s2[k]-'a';
            if(qs.get(ci).isEmpty()){
               fail = true;
               break;
            }
            
            int i = qs.get(ci).poll();
            
            for(int j = 0; j < ci; j++){
               while(!qs.get(j).isEmpty() && qs.get(j).peek() < i){
                  qs.get(j).poll();
               }
            }
         }
         
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}