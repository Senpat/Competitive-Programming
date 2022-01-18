//make sure to make new file!
import java.io.*;
import java.util.*;

public class D766{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      ArrayList<ArrayList<Integer>> divs = new ArrayList<ArrayList<Integer>>(N);
      for(int k = 0; k < N; k++){
         divs.add(new ArrayList<Integer>());
         divs.get(k).add(1);
      }
      
      for(int k = 2; k < N; k++){
         for(int j = k; j < N; j += k){
            divs.get(j).add(k);
         }
      }
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] gcds = new int[N];
      Arrays.fill(gcds,-1);
      
      for(int k = 0; k < n; k++){
         for(int div : divs.get(array[k])){
            if(gcds[div] == -1) gcds[div] = array[k];
            else gcds[div] = gcd(gcds[div],array[k]);
         }
      }
      
      int answer = 0;
      for(int k = 0; k < N; k++){
         if(gcds[k] == k) answer++;
      }
      
      answer -= n;
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } 
      if(b > a){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
      
}