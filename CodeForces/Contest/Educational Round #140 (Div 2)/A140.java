//make sure to make new file!
import java.io.*;
import java.util.*;

public class A140{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         HashSet<Integer> x = new HashSet<Integer>();
         HashSet<Integer> y = new HashSet<Integer>();
         
         f.readLine();
         
         for(int k = 0; k < 3; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
         
            x.add(a);
            y.add(b);
         }
         
         if(x.size() == 3 || y.size() == 3) out.println("YES");
         else out.println("NO");
      }
      
      
      
      
      out.close();
   }
   
      
}