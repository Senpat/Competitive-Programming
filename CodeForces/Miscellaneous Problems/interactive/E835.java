//make sure to make new file!
import java.io.*;
import java.util.*;
 //TOO MANY QUERIES :(
public class E835{
 
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      ArrayList<Integer> set1 = new ArrayList<Integer>();
      ArrayList<Integer> set2 = new ArrayList<Integer>();
      
      int i = 1024;
      while(i >= 2){
         if(i > 2*n){
            i /= 2;
            continue;
         }
         for(int k = 0; k < n; k++){
            if(k%i < i/2) set1.add(k+1);
            else set2.add(k+1);
         }
         
         int q = i == 2 ? y : query(set1,0,set1.size()-1);
         if(q != 0 && q != x){
            break;
         }
         set1 = new ArrayList<Integer>();
         set2 = new ArrayList<Integer>();
         i/=2;
      }
      
      int a = bs(set1,x,y);
      int b = bs(set2,x,y);
      
      out.println("! " + Math.min(a,b) + " " + Math.max(a,b));
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int bs(ArrayList<Integer> alist,int x, int y)throws IOException{
      
      int l = 0;
      int r = alist.size()-1;
      int ans = r;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         int i = query(alist,l,mid);
         if(i == 0 || i == x){
            l = mid+1;
         } else {
            ans = mid;
            r = mid-1;
         }
      }
      
      return alist.get(ans);
      
      
      
      
   }
   
   public static int query(ArrayList<Integer> alist, int l, int r)throws IOException{
      StringJoiner sj = new StringJoiner(" ");
      sj.add("?");
      sj.add("" + (r-l+1));
      for(int k = l; k <= r; k++){
         sj.add("" + alist.get(k));
      }
      
      out.println(sj.toString());
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
      
}