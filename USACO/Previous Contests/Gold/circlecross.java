/*
TASK: circlecross
LANG: JAVA
*/
//semi-t
import java.io.*;
import java.util.*;

class circlecross{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      int[] array = new int[2*n+1];
      
      for(int k = 1; k <= 2*n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      bits = new int[2*n+1];
      
      //HashSet<Integer> seen = new HashSet<Integer>();
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      int answer = 0;
      for(int k = 1; k <= 2*n; k++){
         if(!map.containsKey(array[k])){
            map.put(array[k],k);
            update(k,1);
         } else {
            update(map.get(array[k]),-1);
            answer+=psums(k)-psums(map.get(array[k])-1);
            //System.out.println(answer + " " + k);
         }
      }
      
      out.println(answer);
      System.out.println(answer);
      
        
      out.close();
   }
   
   public static void update(int i, int x){
      for(; i <= 2*n; i+=i&-i){
         bits[i] += x;
      }
   }
   
   public static int psums(int i){
      int sum = 0;
      for(; i > 0; i-=i&-i){
         sum += bits[i];
      }
      return sum;
   }
   
      
}