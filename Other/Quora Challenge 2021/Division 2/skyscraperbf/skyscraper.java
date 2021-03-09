//make sure to make new file!
import java.io.*;
import java.util.*;
//brute force
public class skyscraper{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            int x = Integer.parseInt(st.nextToken())-1;
            
            int li = x-1;
            int lcount = 0;
            int curmax = 0;
            while(li >= 0){
               if(array[li] > array[x]) break;
               if(array[li] >= curmax){
                  curmax = array[li];
                  lcount++;
               }
               li--;
            }
            
            int ri = x+1;
            int rcount = 0;
            curmax = 0;
            while(ri <= n-1){
               if(array[ri] > array[x]) break;
               if(array[ri] >= curmax){
                  curmax = array[li];
                  rcount++;
               }
               ri++;
            }
            
            out.println(lcount+rcount+1);
            
            
            
         } else if(i == 2){
            int x = Integer.parseInt(st.nextToken())-1;
            int h = Integer.parseInt(st.nextToken());
            
            array[x] = h;
            
         } else if(i == 3){
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int h = Integer.parseInt(st.nextToken());
            
            for(int j = x; j <= y; j++){
               array[j] = h;
            }
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}