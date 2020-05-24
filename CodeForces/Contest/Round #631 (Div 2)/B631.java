//make sure to make new file!
import java.io.*;
import java.util.*;

public class B631{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         
         int[] freq = new int[n+1];
         int max1 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freq[array[k]]++;
            max1 = Math.max(max1,array[k]);
         }
         
         int max2 = 0;
         boolean fail2 = false;
         for(int k = 1; k <= n; k++){
            if(freq[k] == 2){
               max2 = k;
            }
            if(freq[k] > 2){
               fail2 = true;
            }
         }
         
         if(fail2 || max1+max2 != n){
            out.println("0");
            continue;
         }
         
         boolean b21 = check(array,max2,max1);
         boolean b12 = check(array,max1,max2);
         
         int answer = 0;
         if(b21) answer++;
         if(b12) answer++;
         
         if(max2 == 0){
            if(b12 || b12){
               out.println("1");
               out.println(n);
            } else {
               out.println("0");
            }
         } else if(max2 == max1){
            if(b12 || b12){
               out.println("1");
               out.println(max2 + " " + max1);
            } else {
               out.println("0");
            }
         } else {
            out.println(answer);
            if(b21) out.println(max2 + " " + max1);
            if(b12) out.println(max1 + " " + max2);
         }
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int i1, int i2){
      
      int max = 0;
      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 0; k < i1; k++){
         hset.add(array[k]);
         max = Math.max(max,array[k]);
      }
      
      if(max != i1 || hset.size() != i1) 
         return false;
      
      max = 0;
      hset = new HashSet<Integer>();
      
      for(int k = i1; k < array.length; k++){
         hset.add(array[k]);
         max = Math.max(max,array[k]);
      }
      
      
      if(max != i2 || hset.size() != i2) 
         return false;
      return true;
   }
      
}