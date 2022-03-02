//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());

         queries = new char[2*n+1][2*n+1];
      
         TreeSet<Integer> low = new TreeSet<Integer>();
         TreeSet<Integer> second = new TreeSet<Integer>();
         
         TreeSet<Integer> issecond = new TreeSet<Integer>();
         
         for(int k = 1; k <= n; k++){
            int a = k*2-1;
            int b = k*2;
            
            if(query(a,b) == '>'){
               low.add(b);
               issecond.add(a);
            } else {
               low.add(a);
               issecond.add(b);
            }
         }
         
         //get n lower half ones
         for(int k = 0; k < n; k++){
            int worst = -1;
            for(int i : second){
               if(worst == -1) worst = i;
               else if(query(worst,i) == '>'){
                  worst = i;
               }
            }
            for(int i : low){
               if(worst == -1) worst = i;
               else if(query(worst,i) == '>'){
                  worst = i;
               }
            }
            
            if(issecond.contains(worst)){
               second.remove(worst);
            } else {
               low.remove(worst);
               second.add(next(worst));
            }
            
         }
         
         out.println("!");
         out.flush();
            
         

      }
      
      
      
      
      out.close();
   }
   
   public static int next(int i){
      if(i % 2 == 1) return i+1;
      else return i-1;
   }
   
   public static char[][] queries;
   
   public static char query(int a, int b) throws IOException{
      if(queries[a][b] == '>' || queries[a][b] == '<') return queries[a][b];
      out.println("? " + a + " " + b);
      out.flush();
      
      String s = f.readLine();
      return queries[a][b] = s.charAt(0);
   }
   
      
}