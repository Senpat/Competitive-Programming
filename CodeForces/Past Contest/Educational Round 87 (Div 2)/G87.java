//make sure to make new file!
import java.io.*;
import java.util.*;

public class G87{

   public static BufferedReader f;
   public static PrintWriter out;
   public static int n;
   
   public static Random rand;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      rand = new Random();
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(!check1()){
            out.println("! 1");
            out.flush();
            continue;
         }
         
         int l = 1;
         int r = 1;
         
         while(true){            
            l = r+1;
            r = Math.min(n,l+r-1);
            
            String s = queryint1(l,r);
            if(s.equals("FIRST")){
               break;
            }
         }
         
         //l and r are set - edges of interval
         int start = l;
         int ans = r;
         while(l <= r){
            int mid = l + (r-l)/2;
            
            String s = queryint1(start,mid);
            
            if(s.equals("EQUAL")){
               l = mid+1;
            } else {
               ans = mid;
               r = mid-1;
            }
         }
         
         out.println("! " + ans);
         out.flush();
            
      

      }
      
      
      
      
      out.close();
   }
   
   public static String queryint1(int l, int r) throws IOException{
      int size = r-l+1;
      
      out.println("? " + size + " " + size);
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= size; k++){
         sj.add("" + k);
      }
      out.println(sj.toString());
      sj = new StringJoiner(" ");
      for(int k = l; k <= r; k++){
         sj.add("" + k);
      }
      out.println(sj.toString());
      out.flush();
      
      return f.readLine();
   }
   
   public static boolean check1() throws IOException{
      for(int k = 0; k < 20; k++){
         int i = rand.nextInt(n-1) + 2;         //generate random number between 2 and n inclusive
         out.println("? 1 1");
         out.println("1");
         out.println(i);
         out.flush();
         
         String s = f.readLine();
         
         if(s.equals("SECOND")) return false;
      }
      
      return true;
   }      
         
   
      
}