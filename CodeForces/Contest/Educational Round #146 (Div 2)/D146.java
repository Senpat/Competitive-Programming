//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong sol, can't use binary search
public class D146{
   
   public static int n;
   public static long m;
   public static Weapon[] weapons;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Long.parseLong(st.nextToken());
         
         StringTokenizer stf = new StringTokenizer(f.readLine());
         StringTokenizer std = new StringTokenizer(f.readLine());
         
         weapons = new Weapon[n];
         for(int k = 0; k < n; k++){
            long fi = Long.parseLong(stf.nextToken());
            long di = Long.parseLong(std.nextToken());
            weapons[k] = new Weapon(fi,di);
         }
         
         Arrays.sort(weapons);
         
         int l = 1;
         int r = n;
         int ans = 0;
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //first mid weapons don't move
            if(check(mid)){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         } 
        
         out.println(n-ans);
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int x){
      if(weapons[0].f-weapons[x-1].f > m) return false;
      
      long l = weapons[0].f-m;
      long r = weapons[x-1].f;
      
      for(long k = Math.max(0,l); k <= r; k++){
         boolean fail = false;
         for(int j = x; j < n; j++){
            //check if you can put weapons[j] in this range
            if((k+m)/weapons[j].f * weapons[j].f < k){
               fail = true;
               break;
            }
         }
         if(!fail) return true;
      }
      
      return false;
   }
   
   public static class Weapon implements Comparable<Weapon>{
      long f;
      long d;
      long p;
      public Weapon(long a, long b){
         f = a;
         d = b;
         p = f*d;
      }
      
      //sort in decreasing order of f
      public int compareTo(Weapon w){
         if(f > w.f) return -1;
         if(f < w.f) return 1;
         return 0;
      }
   }
}