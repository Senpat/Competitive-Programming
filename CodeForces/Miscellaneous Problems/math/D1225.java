//Power Products
import java.io.*;
import java.util.*;
//tle tc6
public class D1225{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      HashMap<HashMap<Integer,Integer>,Long> map = new HashMap<HashMap<Integer,Integer>,Long>();
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         HashMap<Integer,Integer> facs = new HashMap<Integer,Integer>();
         HashMap<Integer,Integer> oppo = new HashMap<Integer,Integer>();
         
         int x = array[k];
         for(int i = 2; i*i <= x; i++){
            if(x%i == 0){
               int num = 0;
               while(x%i == 0){
                  num++;
                  x/=i;
               }
               if(num%m!=0){
                  facs.put(i,num%m);
                  oppo.put(i,m-num%m);
               }
            }
         }
         
         if(x!=1){
            facs.put(x,1);
            oppo.put(x,m-1);
         }
         
         if(map.containsKey(oppo)){
            answer += map.get(oppo);
         }
         
         if(map.containsKey(facs)){
            map.put(facs,map.get(facs)+1);
         } else {
            map.put(facs,1L);
         }
         
         
      }
      
      
      out.println(answer);
      

      
      
      
      
      
      out.close();
   }
   
      
}