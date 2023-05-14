//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class C862b{

   public static ArrayList<Long> all;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         Long[] lines = new Long[n];
         ArrayList<Long> pos = new ArrayList<Long>();
         ArrayList<Long> neg = new ArrayList<Long>();
         for(int k = 0; k < n; k++){
            lines[k] = Long.parseLong(f.readLine());
            
            if(lines[k] >= 0) pos.add(lines[k]);
            else neg.add(lines[k]);
         }
         
         Collections.sort(pos);
         Collections.sort(neg);
         
         all = new ArrayList<Long>();
         for(int k = 0; k < pos.size(); k++){
            all.add(pos.get(k));
         }
         for(int k = 0; k < neg.size(); k++){
            all.add(neg.get(k));
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            //for if parabola is above -> always NNNYYYNNN
            //check mid
            if(!check(a,b,c,all.get(0))){
               out.println("YES");
               out.println(all.get(0));
               continue;
            }
            
            if(!check(a,b,c,all.get(n-1))){
               out.println("YES");
               out.println(all.get(n-1));
               continue;
            }
            
            //parabola might be on the right , YYYYYYNYYYYYY, left Y's intersect positive, right intersect negative
            long right_parabola = bs(a,b,c,true);
            if(right_parabola != Long.MAX_VALUE){
               out.println("YES");
               out.println(right_parabola);
               continue;
            }
            
            //parabola might be on the left , YYYYYYNYYYYYY, left Y's intersect negative, right intersect positive
            long left_parabola = bs(a,b,c,false);
            if(left_parabola != Long.MAX_VALUE){
               out.println("YES");
               out.println(left_parabola);
               continue;
            }
            out.println("NO");
         }
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static long bs(long a, long b, long c, boolean right){
      int n = all.size();
   
      double ad = (double)a;
      double bd = (double)b;
      double cd = (double)c;
      
      int l = 0;
      int r = n-1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         if(!check(a,b,c,all.get(mid))){
            return all.get(mid);
         } else {
            //find intersection
            double midd = (double)all.get(mid);
            double curb = bd-midd;
            double x = (-curb - Math.sqrt(curb*curb - 4.0*ad*cd))/(2.0*ad);
            
            //System.out.println(mid + " " + all.get(mid) + " " + x + " " + (ad*x*x + bd * x + cd));
            
            if(ad*x*x + bd * x + cd >= 0){
               if(right){
               //go right
                  l = mid+1;
               } else {
                  r = mid-1;
               }
            } else {
               if(right){
                  r = mid-1;
               } else {
                  l = mid+1;
               }
            }
         }
      }
      
      return Long.MAX_VALUE;
   }
   
   public static boolean check(long a, long b, long c, long k){
      return (b-k)*(b-k) >= 4L * a * c;
   }
   
      
}