//make sure to make new file!
import java.io.*;
import java.util.*;

public class C632{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[] psums = new long[n+1];
      psums[0] = 0L;
      for(int k = 0; k < n; k++){
         psums[k+1] = psums[k] + array[k];
      }
      
      HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();
      
      hmap.put(0L,1);
      
      int l = 0;
      int r = 1;
      
      long answer = 0L;
      while(r <= n){
         
         //check if range is good
         if(hmap.containsKey(psums[r])){
            
            
            
            //remove
            if(hmap.get(psums[l]) == 1){
               hmap.remove(psums[l]);
            } else {
               hmap.put(psums[l],hmap.get(psums[l])-1);
            } 
            l++; 
            if(l==r){
               if(hmap.containsKey(psums[r])){
                  hmap.put(psums[r],hmap.get(psums[r])+1);
               } else {
                  hmap.put(psums[r],1);
               }
               r++;
            }
         } else {
            answer += r-l;
            if(hmap.containsKey(psums[r])){
               hmap.put(psums[r],hmap.get(psums[r])+1);
            } else {
               hmap.put(psums[r],1);
            }
            
            r++;
         }
      
      }
      
      out.println(answer);
      
   
      
      
      
      
      
      out.close();
   }
   
      
}