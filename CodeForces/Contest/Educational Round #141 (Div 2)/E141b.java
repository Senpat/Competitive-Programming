//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E141b{

   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         n = Integer.parseInt(f.readLine());
      
         StringTokenizer sta = new StringTokenizer(f.readLine());
         StringTokenizer stb = new StringTokenizer(f.readLine());
         
         int[] arraya = new int[n];
         int[] arrayb = new int[n];
         
         for(int k = 0; k < n; k++){
            arraya[k] = Integer.parseInt(sta.nextToken());
            arrayb[k] = Integer.parseInt(stb.nextToken());
         }
         
         ArrayList<Integer> a = new ArrayList<Integer>();
         ArrayList<Integer> b = new ArrayList<Integer>();
         
         int maxa = 1;
         
         for(int k = 0; k < n; k++){
            if(arraya[k] > arrayb[k]){
               a.add(arraya[k]);
               b.add(arrayb[k]);
               
               maxa = Math.max(maxa,arraya[k]);
            }
         }
         
         if(a.size() == 0){
            out.println(n);
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 1; k <= n; k++){
               sj.add("" + k);
            }
            out.println(sj.toString());
            continue;
         }
         
         
         
         bits = new int[n+1];
         for(int k = 0; k < a.size(); k++){
            update(b.get(k),1);
            update(a.get(k),-1);
         }
         
         
         StringJoiner sj = new StringJoiner(" ");
         int an = 0;
         for(int k = 1; k <= n; k++){
            boolean found = false;
            for(int j = k; j <= n; j += k){
               if(psum(j) >= 1){
                  found = true;
                  break;
               }
            }
            if(!found){
               an++;
               sj.add("" + k);
            }
         }
         
         out.println(an);
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static class Range{
      int l1;
      int r1;
      int l2;
      int r2;
      public Range(int a, int b, int c, int d){
         l1 = a;
         r1 = b;
         l2 = c;
         r2 = d;
      }
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