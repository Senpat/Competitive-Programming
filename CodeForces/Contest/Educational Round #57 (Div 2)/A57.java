//make sure to make new file!
import java.io.*;
import java.util.*;

public class A57{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         out.println(n + " " + (n*2));
      
      }
      
      out.close();
   }
   
}