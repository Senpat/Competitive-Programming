//make sure to make new file!
import java.io.*;
import java.util.*;
//danny trick: xor
public class Multisets{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      for(int t = 0; t < q; t++){
         StringTokenizer st = new StringTokenizer(f.readLine());
           
         int qt = Integer.parseInt(st.nextToken());
         
         if(qt == 1){
            int m = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            if(x%2 == 0) alist.add(0);
            else alist.add(m);
         } else if(qt == 2){
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            
            alist.add(alist.get(x) ^ alist.get(y));
         } else if(qt == 3){
            int x = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            if(k%2 == 0) alist.add(alist.get(x));
            else alist.add(alist.get(x) ^ m);
         } else if(qt == 4){
            int x = Integer.parseInt(st.nextToken())-1;
            
            out.println(alist.get(x));
         }
         
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}