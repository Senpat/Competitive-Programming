//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;

public class Fdoubleup{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200;
      ArrayList<BigInteger> pow2 = new ArrayList<BigInteger>(N);
      HashMap<String,Integer> topow2 = new HashMap<String,Integer>();
      
      pow2.add(new BigInteger("1"));
      topow2.put("1",0);
      BigInteger two = new BigInteger("2");
      for(int k = 1; k < N; k++){
         pow2.add(pow2.get(k-1).multiply(two));
         topow2.put(pow2.get(k).toString(),k);
      }
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      ArrayList<Integer> cur = new ArrayList<Integer>(n);
      for(int k = 0; k < n; k++){
         cur.add(topow2.get(st.nextToken()));
      }
      
      while(cur.size() > 1){
         ArrayList<Integer> next = new ArrayList<Integer>();
         int i = 0;
         while(i < cur.size()){
            //last number, must add
            if(next.size() == 0 && i == cur.size()-1){
               next.add(cur.get(i));
               i++;
               continue;
            }
         
            //check if you can combine
            if(i+1 < cur.size() && (int)cur.get(i) == (int)cur.get(i+1)){
               next.add(cur.get(i)+1);
               i += 2;
               continue;
            }
            
            //check if it is too small
            boolean left = true;
            if(next.size() > 0 && (int)next.get(next.size()-1) <= (int)cur.get(i)){
               left = false;
            }
            boolean right = true;
            if(i+1 < cur.size() && (int)cur.get(i+1) <= cur.get(i)){
               right = false;
            }
            
            if(left && right){
               //too small
               i++;
            } else {
               next.add(cur.get(i));
               i++;
            }
            
         }
         
         cur = next;
      }
      
      out.println(pow2.get(cur.get(0)).toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}