//make sure to make new file!
import java.io.*;
import java.util.*;

public class FG12{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int[] freq = new int[100005];
         int maxfreq = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freq[array[k]]++;
            maxfreq = Math.max(maxfreq,freq[array[k]]);
         }
         
         if(maxfreq > (n+1)/2){
            out.println(-1);
            continue;
         }
         
         ArrayList<Integer> borders = new ArrayList<Integer>();
         borders.add(0);
         for(int k = 1; k < n; k++){
            if(array[k-1] == array[k]) borders.add(k);
         }
         borders.add(n);
         
         if(borders.size() == 2){
            out.println(0);
            continue;
         }
         
         int numsections = borders.size()-1;
         int[] doublefreq = new int[100005];
         for(int k = 0; k < borders.size()-1; k++){
            if(array[borders.get(k)] == array[borders.get(k+1)-1]){
               doublefreq[array[borders.get(k)]]++;
            }
         }
         
         int max = 0;
         int maxnum = -1;
         for(int k = 0; k < 100005; k++){
            if(max < doublefreq[k]){
               max = doublefreq[k];
               maxnum = k;
            }
         }
         
         int answer = numsections-1;
         
         if(max >= (numsections+1)/2){
            answer += max-(numsections+1)/2;
            
            //get number of sections that have 1 of maxnum
            int num1 = 0;
            for(int k = 0; k < borders.size()-1; k++){
               if((array[borders.get(k)]==maxnum) != (array[borders.get(k+1)-1]==maxnum)){
                  num1++;
               }
            }
            
            if(numsections%2 == 0){
               answer += Math.max(0,num1-1);
            } else {
               answer += num1;
            }
         }
         out.println(answer);
         
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}