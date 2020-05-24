//make sure to make new file!
import java.io.*;
import java.util.*;

public class B713{
   
   public static BufferedReader f;
   public static PrintWriter out; 
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      int l;
      int r;
      int mid;
      int ans;
      
      
      l = 1;
      r = n;
      ans = -1;
      //see if you can draw vertical line between them
      
      while(l <= r){
         mid = l + (r-l)/2;
         if(mid == n) break;
         
         int il = query(1,1,n,mid);
         int ir = query(1,mid+1,n,n);
         
         if(il == 1 && ir == 1){
            ans = mid;
            break;
         }
         
         if(il > ir){
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      int x11 = -1;
      int y11 = -1;
      int x12 = -1;
      int y12 = -1;
      int x21 = -1;
      int y21 = -1;
      int x22 = -1;
      int y22 = -1;
      if(ans == -1){
         //find horizontal line
         l = 1;
         r = n;
         ans = -1;
      
         while(l <= r){
            mid = l + (r-l)/2;
         
         
            int il = query(1,1,mid,n);
            int ir = query(mid+1,1,n,n);
         
            if(il == 1 && ir == 1){
               ans = mid;
               break;
            }
         
            if(il > ir){
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
      
         int bar = ans;
         
                  //find top line of top block
         l = 1;
         r = bar;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(mid,1,bar,n);
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         x11 = ans;
         
         //find bottom line of top block
         l = 1;
         r = bar;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,1,mid,n);
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         x12 = ans;
         
         //find left of top block
         
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,mid,bar,n);
            
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         y11 = ans;
         
         //find right of top block
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,1,bar,mid);
            
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         y12 = ans;
         
         
         
         
         
         //find top line of bottom block
         l = bar+1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(mid,1,n,n);
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         x21 = ans;
         
         //find bottom line of bottom block
         l = bar+1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(bar+1,1,mid,n);
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         x22 = ans;
         
         //find left of bottom block
         
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(bar+1,mid,n,n);
            
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         y21 = ans;
         
         //find right of bottom block
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(bar+1,1,n,mid);
            
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         y22 = ans;
         
         
         
         
         
       
      } else {
         //ans is the vertical line between
         int bar = ans;
         //find left line of left block
         l = 1;
         r = bar;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,mid,n,bar);
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         y11 = ans;
         
         //find right line of left block
         l = 1;
         r = bar;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,1,n,mid);
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         y12 = ans;
         
         //find top of left block
         
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(mid,1,n,bar);
            
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         x11 = ans;
         
         //find bottom of left block
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,1,mid,bar);
            
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         x12 = ans;
         
         
         
         
         
         //find left line of right block
         l = bar+1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,mid,n,n);
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         y21 = ans;
         
         //find right line of right block
         l = bar+1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,bar+1,n,mid);
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         y22 = ans;
         
         //find top of right block
         
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(mid,bar+1,n,n);
            
            if(i == 1){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         x21 = ans;
         
         //find bottom of right block
         l = 1;
         r = n;
         ans = -1;
         while(l <= r){
            mid = l + (r-l)/2;
            
            int i = query(1,bar+1,mid,n);
            
            if(i == 1){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         x22 = ans;
         
      
      }
      
      out.println("! " + x11 + " " + y11 + " " + x12 + " " + y12 + " " + x21 + " " + y21 + " " + x22 + " " + y22);
      
      
      
      
      out.close();
   }
   
   public static int query(int a,int b, int c, int d)throws IOException{
      out.println("? " + a + " " + b + " " + c + " " + d);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
      
}