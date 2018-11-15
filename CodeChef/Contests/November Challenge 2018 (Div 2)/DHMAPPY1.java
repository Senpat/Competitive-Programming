//make sure to make new file!
import java.io.*;
import java.util.*;

public class DHMAPPY1{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      LinkedList<Integer> list = new LinkedList<Integer>();
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         list.add(Integer.parseInt(st.nextToken()));
      }
      
      String qu = f.readLine();
      
      for(int k = 0; k < q; k++){
         char c = qu.charAt(k);
         
         //right shift
         if(c == '!'){
            list.addFirst(list.pollLast());
         } else {
            int max = 0;
            int cur = 0;
            for(Integer i : list){
               if(i == 1){
                  cur++;
                  max = Math.max(cur,max);
               } else {
                  cur = 0;
               }
            }
            out.println(Math.min(max,m));
         }
      }
         
         
         
         
      out.close();
   }
   
}