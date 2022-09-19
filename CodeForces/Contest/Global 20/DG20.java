//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG20{

   public static int n;
   //public static int N;
   public static int[] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         int[] a = new int[n+1];
         int[] b = new int[n+1];
         for(int k = 1; k <= n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
         }
         
         //N = n+1;
         bits = new int[n+1];
         
         ArrayList<Queue<Integer>> adj = new ArrayList<Queue<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new LinkedList<Integer>());
         
         for(int k = 1; k < n; k++){
            if(b[k] == b[k+1]){
               adj.get(b[k]).add(k);
            }
         }
         
         boolean fail = false;
         for(int k = 1; k <= n; k++){
            int offset = psum(n)-psum(k-1);
            int bi = k-offset;
            if(a[k] == b[bi]) continue;
            
            int next;
            if(adj.get(a[k]).isEmpty()){
               fail = true;
               break;
            } else {
               next = adj.get(a[k]).poll();
            }
            
            update(Math.min(n,next+offset+1),1);
         }
         
         if(a[n] != b[n]) fail = true;
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
            
         
      }
      
      
      
      
      out.close();
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
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