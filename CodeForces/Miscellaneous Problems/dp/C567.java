//Geometric Progressions
import java.io.*;
import java.util.*;
import java.math.*;

public class C567{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<Long> alist = new ArrayList<Long>();
      
      HashMap<Long, Long> total = new HashMap<Long,Long>();
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         long i = Long.parseLong(st.nextToken());
         if(total.containsKey(i)){
            total.put(i,total.get(i)+1);
         } else {
            total.put(i,1L);
         }
         if(i!=0L) alist.add(i);
      }
      
      long answer = 0L;
      
      if(total.containsKey(0L) && total.get(0L)>=3){
         answer += total.get(0L)*(total.get(0L)-1)*(total.get(0L)-2)/6;
      }
      
      if(alist.size()<3 && m!=1){
         out.println(answer);
         out.close();
         System.exit(0);
      }
      
      if(m==1){
      
         for(long i : total.keySet()){
            if(i!=0L && total.get(i)>=3){
               answer += total.get(i)*(total.get(i)-1)*(total.get(i)-2)/6;
            }
         }
         out.println(answer);
         out.close();
         System.exit(0);
      }
      
      
      
      HashMap<Long,Long> curf = new HashMap<Long,Long>();
      
      for(long i : total.keySet()){
         curf.put(i,0L);
      }
      
      for(int k = 0; k < alist.size()-1; k++){
         long i = alist.get(k);
         
         if(i%m==0 && total.containsKey(i/m) && total.containsKey(i*m)) answer += curf.get(i/m) * (total.get(i*m)-curf.get(i*m));
         curf.put(i,curf.get(i)+1);
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
}