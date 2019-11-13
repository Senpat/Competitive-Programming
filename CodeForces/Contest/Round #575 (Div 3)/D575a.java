//make sure to make new file!
import java.io.*;
import java.util.*;

public class D575a{

   public static HashMap<Character,Character> next;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      next = new HashMap<Character,Character>();
      next.put('R','G');
      next.put('G','B');
      next.put('B','R');
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         String s = f.readLine();
         
         int min = m;
         
         for(int k = 0; k <= n-m; k++){
            int r = count(s,k,k+m,'R');
            int g = count(s,k,k+m,'G');
            int b = count(s,k,k+m,'B');
            
            min = Math.min(min,Math.min(r,Math.min(g,b)));
         }
         
         out.println(min);
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static int count(String s, int l, int r, char c){
      int count = 0;
      for(int k = l; k < r; k++){
         if(s.charAt(k) != c) count++;
         c = next.get(c);
      }
      return count;
   }
      
   
}