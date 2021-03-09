//make sure to make new file!
import java.io.*;
import java.util.*;

public class message{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = 0;
         ArrayList<Integer> alist = new ArrayList<Integer>();
         int missingindex = -1;
         
         while(st.hasMoreTokens()){
            int i = Integer.parseInt(st.nextToken());
            alist.add(i);
            if(i == -1) missingindex = n;
            n++;
         }
         
         if(missingindex == 0 || missingindex == 1){
            out.println(alist.get(3));
         } else {
            out.println(alist.get(0));
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}