//make sure to make new file!
import java.io.*;
import java.util.*;

public class C344{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Man[] mans = new Man[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         mans[k] = new Man(a,b);
      }
      
      ArrayList<Man> dec = new ArrayList<Man>();
      
      int max = -1;
      
      for(int k = m-1; k >= 0; k--){
         if(mans[k].i > max){
            max = mans[k].i;
            dec.add(mans[k]);
         }
      }
      
      Collections.reverse(dec);
      
      ArrayList<Man> critical = new ArrayList<Man>();
      
      critical.add(dec.get(0));
      for(int k = 1; k < dec.size(); k++){
         if(dec.get(k).t != dec.get(k-1).t) critical.add(dec.get(k));
      }
      
      int maxman = critical.get(0).i;
      
      int[] answer = new int[n];
      
      for(int k = maxman; k < n; k++){
         answer[k] = array[k];
      }
      
      //put elements that will be sorted in a list so that you can sort
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      for(int k = 0; k < maxman; k++){
         alist.add(array[k]);
      }
      
      Collections.sort(alist);
      
      int l = 0;
      int r = maxman-1;
      
      critical.add(new Man(0,0));
      for(int k = 0; k < critical.size()-1; k++){
         int start = critical.get(k+1).i;
         int end = critical.get(k).i-1;
         
         if(critical.get(k).t == 1){
            //get from end
            for(int j = end; j >= start; j--){
               answer[j] = alist.get(r);
               r--;
            }
         } else {
            //get from start
            for(int j = end; j >= start; j--){
               answer[j] = alist.get(l);
               l++;
            }
         }
      }
      
      
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      out.close();
   }
         
   public static class Man{
      int t;
      int i;
      public Man(int a, int b){
         t = a;
         i = b;
      }
   }
   
      
}