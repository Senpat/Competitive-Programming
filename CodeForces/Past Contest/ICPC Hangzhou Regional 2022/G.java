//make sure to make new file!
import java.io.*;
import java.util.*;
/*
fails for the case 
6 6
1 2
2 3
3 1
4 1
5 2
6 3
*/
public class G{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         if(m == n-1){
            out.println("YES");
         } else if(m == n){
            boolean all2 = true;
            for(int k = 1; k <= n; k++){
               if(adj.get(k).size() != 2){
                  all2 = false;
                  break;
               }
            }
            
            if(all2){
               out.println("YES");
            } else {
               out.println("NO");
            }
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}