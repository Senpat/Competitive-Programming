//make sure to make new file!
import java.io.*;
import java.util.*;

public class B340{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         if(qt == 1) alist.add(x);
         else {
            //qt == 2
            out.println(alist.get(alist.size()-x));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}