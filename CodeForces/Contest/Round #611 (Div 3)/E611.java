//make sure to make new file!
import java.io.*;
import java.util.*;

public class E611{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      Integer[] array = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(array);
      
      //calculate min
      int bot = array[0];
      
      int sections = 1;
      
      for(int k = 1; k < n; k++){
         if(array[k] > bot+2){
            sections++;
            bot = array[k];
         }
      }
      
      int minanswer = sections;
      
      
      HashSet<Integer> answer = new HashSet<Integer>();
      HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      
      for(int k = 0; k < n; k++){
         if(freq.containsKey(array[k])){
            freq.put(array[k],freq.get(array[k])+1);
         } else {
            alist.add(array[k]);
            freq.put(array[k],1);
         }
      }
      
      Collections.sort(alist);
      
      for(int k = 0; k < alist.size(); k++){
         int i = alist.get(k);
         if(freq.get(i) == 1){
            if(answer.contains(i)) answer.add(i+1);
            else if(answer.contains(i-1)) answer.add(i);
            else answer.add(i-1);
         } else if(freq.get(i) == 2){
            if(answer.contains(i-1)){
               answer.add(i);
               answer.add(i+1);
            } else {
               answer.add(i);
               answer.add(i-1);
            }
         } else {
            answer.add(i-1);
            answer.add(i);
            answer.add(i+1);
         }
      }
      
      int maxanswer = answer.size();
      
      out.println(minanswer + " " + maxanswer);
      
      
      
      
      
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}