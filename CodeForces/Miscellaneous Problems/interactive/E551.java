//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-t
public class E551{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      //query all columns
      int odd1 = -1;
      int odd2 = -1;
      
      for(int k = 1; k < n; k++){
         out.println("? 1 " + k + " " + n + " " + k);
         out.flush();
         
         int i = Integer.parseInt(f.readLine());
         if(i%2 == 1){
            if(odd1 != -1){
               odd2 = k;
               break;
            } else {
               odd1 = k;
            }
         }
      }
      
      //in same column
      if(odd1 == -1){
         //query all rows
         odd1 = -1;
         odd2 = -1;
      
         for(int k = 1; k < n; k++){
            out.println("? " + k + " 1 " + k + " " + n);
            out.flush();
         
            int i = Integer.parseInt(f.readLine());
            if(i%2 == 1){
               if(odd1 != -1){
                  odd2 = k;
                  break;
               } else {
                  odd1 = k;
               }
            }
         }
         
         
         if(odd2 == -1) odd2 = n;
         
         //binary search
         
         int x1 = odd1;
         
         int y1 = -1;
         
         int l = 1;
         int r = n;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            out.println("? " + odd1 + " " + l + " " + odd1 + " " + (mid));
            out.flush();
            int segl = Integer.parseInt(f.readLine());
            /*
            out.println("? " + odd1 + " " + (mid+1) + " " + odd1 + " " + r);
            out.flush();
            
            int segr = Integer.parseInt(f.readLine());
            */
            if(segl % 2 == 1){
               if(l == mid){
                  y1 = l;
                  break;
               }
               r = mid;
            
            } else {
               if(r == mid+1){
                  y1 = r;
                  break;
               }
               l = mid;
            
            
            }
         }

         
         out.println("! " + odd1 + " " + y1 + " " + odd2 + " " + y1);
         
         
      
      
      } else {
         //binary search in each column
         if(odd2 == -1) odd2 = n;
         
         
         //find in odd1
         
         int x1 = -1;
         int y1 = odd1;
         
         int l = 1;
         int r = n;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            out.println("? " + l + " " + odd1 + " " + (mid) + " " + odd1);
            out.flush();
            int segl = Integer.parseInt(f.readLine());
            /*
            out.println("? " + (mid+1) + " " + odd1 + " " + r + " " + odd1);
            out.flush();
            
            int segr = Integer.parseInt(f.readLine());
            */
            if(segl % 2 == 1){
               if(l == mid){
                  x1 = l;
                  break;
               }
               r = mid;
            
            } else {
               if(r == mid+1){
                  x1 = r;
                  break;
               }
               l = mid;
            
            
            }
         }
         
         
         //find in odd2
         
         int x2 = -1;
         int y2 = odd2;
         
         l = 1;
         r = n;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            out.println("? " + l + " " + odd2 + " " + (mid) + " " + odd2);
            out.flush();
            int segl = Integer.parseInt(f.readLine());
            /*
            out.println("? " + (mid+1) + " " + odd2 + " " + r + " " + odd2);
            out.flush();
            
            int segr = Integer.parseInt(f.readLine());
            */
            if(segl % 2 == 1){
               if(l == mid){
                  x2 = l;
                  break;
               }
               r = mid;
            
            } else {
               if(r == mid+1){
                  x2 = r;
                  break;
               }
               l = mid;
            
            
            }
         }
         
         
         out.println("! " + x1 + " " + y1 + " " + x2 + " " + y2);   
            
            
      
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}