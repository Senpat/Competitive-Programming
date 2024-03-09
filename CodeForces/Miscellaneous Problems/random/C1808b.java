//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong logic for for l/r cases
public class C1808b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         char[] lin = st.nextToken().toCharArray();
         char[] rin = st.nextToken().toCharArray();
         
         
         //easy logic for unequal length
         if(lin.length != rin.length){
            StringJoiner sj = new StringJoiner("");
            for(int k = 0; k < lin.length; k++){
               sj.add("9");
            }
            out.println(sj.toString());
            continue;
         }
         
         int n = rin.length;
         
         /*
         if(q == 79 && n > 10){
            out.println("1");
         }*/
         int[] l = new int[n];
         int[] r = new int[n];
         
         for(int k = 0; k < n; k++){
            l[k] = Character.getNumericValue(lin[k]);
            r[k] = Character.getNumericValue(rin[k]);
         }
         
         int min = 10;
         int max = -1;
         
         int i = 0;
         StringJoiner sj = new StringJoiner("");
         while(i < n && l[i] == r[i]){
            min = Math.min(min,l[i]);
            max = Math.max(max,r[i]);
            
            sj.add("" + l[i]);
            
            i++;
            
         }
         
         if(i == n){
            out.println(sj.toString());
         } else {
            StringJoiner sjl = new StringJoiner("");
            StringJoiner sjr = new StringJoiner("");
            
            sjl.add(sj.toString());
            sjr.add(sj.toString());
            
            int lmin = Math.min(l[i],min);
            int lmax = Math.max(l[i],max);
            int rmin = Math.min(r[i],min);
            int rmax = Math.max(r[i],max);
            
            //do min
            String cur = "";
            int mindiff = 100;
            
            sjl.add("" + l[i]);
            
            if(i+1 < n){
               int lfill = -1;
               if(l[i+1] < l[i]) lfill = l[i];
               else{
                  //either l[i+1] or l[i+1]+1
                  lfill = l[i+1];
                  for(int k = i+1; k < n; k++){
                     if(l[k] < l[i+1]){
                        break;
                     } else if(l[k] > l[i+1]){
                        lfill = l[i+1]+1;
                        break;
                     }
                  }
               }
               
               for(int k = i+1; k < n; k++) sjl.add("" + lfill);
               lmin = Math.min(lmin,lfill);
               lmax = Math.max(lmax,lfill);
            }
            
            if(lmax-lmin < mindiff){
               cur = sjl.toString();
               mindiff = lmax-lmin;
            }
            
            sjr.add("" + r[i]);
            
            if(i+1 < n){
               int rfill = -1;
               if(r[i+1] > r[i]){
                  rfill = r[i];
               } else {
                  //either r[i+1] or r[i+1]-1
                  rfill = r[i+1];
                  for(int k = i+1; k < n; k++){
                     if(r[k] > r[i+1]){
                        break;
                     } else if(r[k] < r[i+1]){
                        rfill = r[i+1]-1;
                        break;
                     }
                  }
               }
               for(int k = i+1; k < n; k++) sjr.add("" + rfill);
               rmin = Math.min(rmin,rfill);
               rmax = Math.max(rmax,rfill);
            }
            if(rmax-rmin < mindiff){
               cur = sjr.toString();
               mindiff = rmax-rmin;
            }
            
            //make rest the same number
            for(int x = l[i]+1; x <= r[i]-1; x++){
               StringJoiner sjx = new StringJoiner("");
               sjx.add(sj.toString());
               
               for(int k = i; k < n; k++) sjx.add("" + x);
               
               int curmin = Math.min(min,x);
               int curmax = Math.max(max,x);
               
               if(curmax-curmin < mindiff){
                  cur = sjx.toString();
                  mindiff = curmax-curmin;
               }
            }
            
            out.println(cur);
            
         }

      }
      
      
      
      
      out.close();
   }
   
      
}