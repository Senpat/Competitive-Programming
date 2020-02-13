//make sure to make new file!
import java.io.*;
import java.util.*;

public class C610{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int qq = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= qq; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int t = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         TreeMap<Integer,Integer> easy = new TreeMap<Integer,Integer>();
         TreeMap<Integer,Integer> hard = new TreeMap<Integer,Integer>();
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         int numeasy = 0;
         int numhard = 0;
         
         for(int k = 0; k < n; k++){
            int dif = Integer.parseInt(st1.nextToken());
            int man = Integer.parseInt(st1.nextToken());
            
            if(dif == 0){
               if(easy.containsKey(man)){
                  easy.put(man,easy.get(man)+1);
               } else {
                  easy.put(man,1);
               }
               numeasy++;
            }
            if(dif == 1){
               if(hard.containsKey(man)){
                  hard.put(man,hard.get(man)+1);
               } else {
                  hard.put(man,1);
               }
               numhard++;
            }
         }
         
         TreeMap<Integer,Integer> preeasy = new TreeMap<Integer,Integer>();
         TreeMap<Integer,Integer> prehard = new TreeMap<Integer,Integer>();
         
         for(int i : easy.keySet()){
            if(i == easy.firstKey()){
               easy.put
               continue;
            }
            
            preeasy
         
         
            
      }
      
      
      
      
      out.close();
   }
   
      
}