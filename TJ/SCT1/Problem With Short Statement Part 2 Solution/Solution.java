//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   
   public static int[] bits;
   public static int N = 200005;
   public static int BUFFER = 100000;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int head = -1;
      int tail = n;
      
      char[] array1 = f.readLine().toCharArray();
      
      int[] numones = new int[n+1];
      numones[0] = 0;
      for(int k = 1; k <= n; k++){
         numones[k] = numones[k-1] + (array1[k-1] == '1' ? 1 : 0);
      }
   
      int answer1 = numones[n] - (numones[n]-numones[n-numones[n]]);
      out.println(answer1);
      
      
      bits = new int[N+1];
      
      int[] array = new int[N];
      
      for(int k = 0; k < n; k++){
         array[k+BUFFER] = Character.getNumericValue(array1[k]);
         if(array[k+BUFFER] == 1){
            update(k+BUFFER,1);
         }
      }
      
      int num1 = numones[n];
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(a == 0){
            array[head+BUFFER] = b;
            update(head+BUFFER,b);
            if(b==1) num1++;
            head--;
            int answer = psum(tail+BUFFER-num1-1);
            out.println(answer);
            
            
         
         
         } else {
            array[tail+BUFFER] = b;
            update(tail+BUFFER,b);
            if(b==1) num1++;
            tail++;
            int answer = psum(tail+BUFFER-num1-1);
            out.println(answer);
         
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
    
   public static void update(int i, int x){
      for(; i <= N; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   }
   
      
}