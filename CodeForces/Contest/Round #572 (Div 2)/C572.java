//make sure to make new file!
import java.io.*;
import java.util.*;

public class C572{

   public static int MAX = 100005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] pow2 = new int[20];
      pow2[0] = 1;
      int[] log2 = new int[MAX];
      log2[1] = 0;
      for(int k = 1; k < 20; k++){
         pow2[k] = pow2[k-1]*2;
         if(pow2[k] < MAX) log2[pow2[k]] = k;
      }
      
      
      int log = log2(n);
      
      State[][] pre = new State[n][log+1];
      
      for(int k = 0; k < n; k++){
         pre[k][0] = new State(array[k],0);
      }
      
      for(int k = 1; k <= log; k++){
         for(int j = 0; j < n-pow2[k]+1; j++){
            int i = pre[j][k-1].i + pre[j+pow2[k-1]][k-1].i;
            int p = pre[j][k-1].p + pre[j+pow2[k-1]][k-1].p;
            if(i >= 10){
               pre[j][k] = new State(i-10, p+1);
            } else {
               pre[j][k] = new State(i,p);
            }
         }
      }
      
      int q = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int answer = pre[l-1][log2[r-l+1]].p;
         out.println(answer);
       }
      
      
      
      out.close();
   }
   
   public static int log2(int n){
      return (int)(Math.log(1.0*n)/Math.log(2.0));
   }
   
   public static class State{
      int i;   //value
      int p;   //points
      public State(int a, int b){
         i = a;
         p = b;
      }
   }
      
}