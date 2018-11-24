//make sure to make new file!
import java.io.*;
import java.util.*;

public class EVENT{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      HashMap<String,Integer> days = new HashMap<String,Integer>();
      
      days.put("sunday",0);
      days.put("monday",1);
      days.put("tuesday",2);
      days.put("wednesday",3);
      days.put("thursday",4);
      days.put("friday",5);
      days.put("saturday",6);
      
      
      for(int k = 0; k < q; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         String s1 = st.nextToken();
         String s2 = st.nextToken();
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         
         int d = days.get(s2) - days.get(s1);
         if(d < 0) d += 7;
            
         int index = d+1;
         
         while(index < a){
            index+=7;
         }
         
         if(index > b){
            out.println("impossible");
            continue;
         }
         //at least 1 solution
         
         if(index + 7 > b){
            out.println(index);
         } else {
            out.println("many");
         }
      
      }
      
      out.close();
   }
   
}