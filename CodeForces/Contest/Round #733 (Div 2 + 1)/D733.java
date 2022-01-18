//make sure to make new file!
import java.io.*;
import java.util.*;

public class D733{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int matches = 0;
         int[] answer = new int[n+1];
         
         HashSet<Integer> added = new HashSet<Integer>();
         ArrayList<Integer> missing = new ArrayList<Integer>();
         for(int k = 1; k <= n; k++){
            if(!added.contains(array[k])){
               answer[k] = array[k];
               added.add(array[k]);
               matches++;
            } else {
               missing.add(k);
            }
         }
         
         ArrayList<Integer> notadded = new ArrayList<Integer>();
         for(int k = 1; k <= n; k++){
            if(!added.contains(k)) notadded.add(k);
         }
         
         //missing stores indeces without a match, notadded store the numbers
         
         if(missing.size() == 1 && (int)missing.get(0) == (int)notadded.get(0)){
            //can't add it. find the other number that wants what missing.get(0) wants and give it to missing.get(0)
            int x = missing.get(0);
            
            int i = -1;
            for(int k = 1; k <= n; k++){
               if(k == missing.get(0)) continue;
               if(array[k] == array[x]){
                  i = k;
                  break;
               }
            }
            
            answer[x] = array[x];
            answer[i] = x;
         } else {
            
            //missing and notadded are sorted
            HashSet<Integer> missinghset = new HashSet<Integer>(missing);
            
            ArrayList<Integer> both = new ArrayList<Integer>();
            ArrayList<Integer> oneof = new ArrayList<Integer>();           //stores numbers in notadded that are not in missing
            
            for(int i : notadded){
               if(missinghset.contains(i)) both.add(i);
               else oneof.add(i);
            }
            
            if(both.size() == 1){
               //find any spot in answer that doesn't have a value and !=both.get(0)
               for(int k = 1; k <= n; k++){
                  if(answer[k] == 0 && k != (int)both.get(0)){
                     answer[k] = both.get(0);
                     break;
                  }
               }
            } else if(both.size() > 0) {
               for(int k = 0; k < both.size()-1; k++){
                  answer[both.get(k)] = both.get(k+1);
               }
               answer[both.get(both.size()-1)] = both.get(0);
            }
            
            //fill in one ofs
            int oneofi = 0;
            for(int k = 1; k <= n; k++){
               if(answer[k] == 0){
                  answer[k] = oneof.get(oneofi);
                  oneofi++;
               }
            }
         }
         
         out.println(matches);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
      
}