import java.io.*;
import java.util.*;

class citystate{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("citystate.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      Map<String,Long> map = new HashMap<String, Long>();
      for(int k = 0; k < num; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         String city = st.nextToken().substring(0,2);
         String state = st.nextToken();
         
         if(!city.equals(state)){
            String cs = city + state;
            if(!map.containsKey(cs)){
               map.put(cs,0L);
            }
            map.put(cs,map.get(cs)+1);
            
         }
      }
      
      int count = 0;
      for(String s : map.keySet()){
         String other = s.substring(2) + s.substring(0,2);
         if(map.containsKey(other)){
            count += map.get(s) * map.get(other);
         }
      }
      
      out.println(count/2);
      out.close();
         
            
   }
}