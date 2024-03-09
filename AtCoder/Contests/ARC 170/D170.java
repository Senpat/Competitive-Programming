//make sure to make new file!
import java.io.*;
import java.util.*;

public class D170{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         Integer[] a = new Integer[n];
         Integer[] b = new Integer[n];
         TreeSet<Integer> atset = new TreeSet<Integer>();
         TreeSet<Integer> btset = new TreeSet<Integer>();
         HashSet<Integer> adup = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            
            if(atset.contains(a[k])) adup.add(a[k]);
            atset.add(a[k]);
            btset.add(b[k]);
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         int[] bgap = new int[n];
         for(int k = 0; k < n; k++){
            int min = Integer.MAX_VALUE;
            Integer floor = atset.floor(b[k]);
            if(floor != null) min = Math.min(min, b[k]-floor);
            Integer ceil = atset.ceiling(b[k]);
            if(ceil != null) min = Math.min(min, ceil-b[k]);
            
            bgap[k] = min;
         }
         
         int maxbgap = 0;
         int bi = n-1;
         boolean found = false;
         for(int k = n-1; k >= 0; k--){
            //alice picks x
            int x = a[k];
            
            //bob picks min, check alice's range (x-b,x+b)
            if(b[0] <= x){
               boolean curfound = false;
               if(k > 0 && x-a[k-1] < b[0]) curfound = true;
               if(k+1 < n && a[k+1]-x < b[0]) curfound = true;
               //bob has a winning strategy of picking b[0]
               if(!curfound) continue;
            }
            
            //bob picks some value > x
            while(bi >= 0 && b[bi] > x){
               maxbgap = Math.max(maxbgap, bgap[bi]);
               bi--;
            }
            
            if(maxbgap >= x){
               //bob has a winning strategy (pick the b that has the maximum gap)
               continue;
            }
            
            //alice eats x, considering this current gap
            
            if(adup.contains(x)){
               //bob has no other winning strategies, eating doesn't open up new gaps
               found = true;
               break;
            }
            
            int left = Integer.MIN_VALUE;
            if(k > 0) left = a[k-1];
            int right = Integer.MAX_VALUE;
            if(k+1 < n) right = a[k+1];
            
            Integer bx = btset.floor(right-x);
            //see if bx works
            if(bx != null && bx > x && bx >= left+x){
               //bob has a winning strategy
            } else{
               //out.println(a[k]);
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("Alice");
         } else {
            out.println("Bob");
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}