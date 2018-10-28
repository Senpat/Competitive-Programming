//make sure to make new file!
import java.io.*;
import java.util.*;

public class C515{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      LinkedList<Integer> list = new LinkedList<Integer>();
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         char c = st.nextToken().charAt(0);
         int m = Integer.parseInt(st.nextToken());
      
         if(c == 'L'){
            list.addFirst(m);
         } else if(c == 'R'){
            list.addLast(m);
         } else {
            int ind = list.indexOf(m);
            out.println(Math.min(ind,list.size()-ind-1));
         }
      }
      
      
      
      out.close();
   }
   
}