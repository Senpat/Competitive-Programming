//make sure to make new file!
import java.io.*;
import java.util.*;

public class B609b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      int[] a = new int[n];
      int[] b = new int[n];
      
            
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st1.nextToken());
         b[k] = Integer.parseInt(st2.nextToken());

      
      }
      
      Arrays.sort(a);
      Arrays.sort(b);
      
      int minanswer = Integer.MAX_VALUE;
      
      for(int k = 0; k < n; k++){
         //make a[0] to b[k]
         int x;
         if(b[k] >= a[0]){
            x = b[k]-a[0];
         } else {
            x = m-a[0] + b[k];
         }
         if(check(a,b,k,m)){
            minanswer = Math.min(x,minanswer);
         }
      }

      
      if(minanswer == Long.MAX_VALUE) a[n+4] = 0;
      out.println(minanswer);
      
      
      out.close();
   }
   
   public static int getdif(int a, int b, int m){
      int x;
         if(a >= b){
            x = a-b;
         } else {
            x = m-b+a;
         }
         return x;
     }
   public static boolean check(int[] a, int[] b, int x,int m){
      int i = getdif(b[x],a[0],m);
      int n = a.length;
      for(int k = 0; k < a.length; k++){
         //System.out.println(k + " " + b[(k+x+n)%n] + " " +  a[k] + " " + getdif(b[(k+x+n)%n],a[k],m));
         if(getdif(b[(k+x+n)%n],a[k],m) != i) return false;
      }
      
      return true;
   }
      

}