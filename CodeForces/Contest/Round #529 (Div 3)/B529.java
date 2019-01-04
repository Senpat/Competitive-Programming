//make sure to make new file!
import java.io.*;
import java.util.*;

public class B529{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      ArrayList<Integer> alist = new ArrayList<Integer>(n);
      
      for(int k = 0; k < n; k++){
         alist.add(Integer.parseInt(st.nextToken()));
      }
      
      Collections.sort(alist);
      
      int answer = Math.min(alist.get(n-1)-alist.get(1),alist.get(n-2)-alist.get(0));
      
      out.println(answer);
      
      
      out.close();
   }
   
}