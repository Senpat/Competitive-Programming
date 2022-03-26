//make sure to make new file!
import java.io.*;
import java.util.*;

public class F777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      int maxi = 0;
      for(int k = 0;  k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] > array[maxi]){
            maxi = k;
         }
      }
      
      
      int[] max2 = new int[n];            //max of 2nd inc seq
      int[] max1 = new int[n];            //max of 1st inc seq
      
      for(int k = 0; k < maxi; k++){
         if(k == 0){
            max1[k] = array[k];
            max2[k] = 0;
         } else {
            if(array[k] > max1[k-1]){
               max1[k] = array[k];
               max2[k] = max2[k-1];
            } else if(array[k] > max2[k-1]){
               max1[k] = max1[k-1];
               max2[k] = array[k];
            } else {
               max1[k] = Integer.MAX_VALUE;
               max2[k] = Integer.MAX_VALUE;
            }
         }
      }
      
      for(int k = n-1; k > maxi; k--){
         if(k == n-1){
            max1[k] = array[k];
            max2[k] = 0;
         } else {
            if(array[k] > max1[k+1]){
               max1[k] = array[k];
               max2[k] = max2[k+1];
            } else if(array[k] > max2[k+1]){
               max1[k] = max1[k+1];
               max2[k] = array[k];
            } else {
               max1[k] = Integer.MAX_VALUE;
               max2[k] = Integer.MAX_VALUE;
            }
         }
      }
      
      int threshl = 0;
      if(maxi > 0) threshl = max2[maxi-1];
      int threshr = 0;
      if(maxi < n-1) threshr = max2[maxi+1];
      
      
      //min val of last val of inc seq if cur val is in dec
      int[] mininc = new int[n];
      Arrays.fill(mininc, Integer.MAX_VALUE);
      //max val of last val of dec seq if cur val is in inc
      int[] maxdec = new int[n];
      Arrays.fill(maxdec, -1);
      
      int answer = 0;
      
      //right side
      mininc[maxi] = threshl;
      maxdec[maxi] = -1;
      for(int k = maxi+1; k < n; k++){
         if(array[k] > array[k-1]){
            maxdec[k] = maxdec[k-1];
            if(array[k] > mininc[k-1]){
               maxdec[k] = Math.max(maxdec[k],array[k-1]);
            }
            
            if(array[k] < maxdec[k-1]){
               mininc[k] = array[k-1];
            }
         }
         
         if(array[k] < array[k-1]){
            mininc[k] = mininc[k-1];
            if(array[k] < maxdec[k-1]){
               mininc[k] = Math.min(mininc[k],array[k-1]);
            }
            
            if(array[k] > mininc[k-1]){
               maxdec[k] = array[k-1];
            }
         }
         
         
         if(array[k] > threshl && Math.max(array[k],maxdec[k]) >= max1[k] && Math.min(array[k],maxdec[k]) >= max2[k]){
            answer++;
         }
      }
      
      //left side
      mininc[maxi] = threshr;
      maxdec[maxi] = -1;
      for(int k = maxi-1; k >= 0; k--){
         if(array[k] > array[k+1]){
            maxdec[k] = maxdec[k+1];
            if(array[k] > mininc[k+1]){
               maxdec[k] = Math.max(maxdec[k],array[k+1]);
            }
            
            if(array[k] < maxdec[k+1]){
               mininc[k] = array[k+1];
            }
         }
         
         if(array[k] < array[k+1]){
            mininc[k] = mininc[k+1];
            if(array[k] < maxdec[k+1]){
               mininc[k] = Math.min(mininc[k],array[k+1]);
            }
            
            if(array[k] > mininc[k+1]){
               maxdec[k] = array[k+1];
            }
         }
         
         
         if(array[k] > threshr && Math.max(array[k],maxdec[k]) >= max1[k] && Math.min(array[k],maxdec[k]) >= max2[k]){
            answer++;
         }
      }
         
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}