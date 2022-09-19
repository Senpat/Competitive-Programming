//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int target = n*n-m-1;
         
         HashMap<Integer,Shortcut> shortcuts = new HashMap<Integer,Shortcut>();
         int biggest = (2*(n-1)-1)*2;
         
         int i = 0;
         int prevouter = -1;
         int previnner = -1;
         for(int k = biggest; k > 0; k-=2){
            //calculate the shortcut
            if(k == biggest){
               prevouter = n/2+1;
            } else {
               prevouter += 2*((n/2)-(i/4));
               if(i%4 == 0) prevouter++;
            }
            
            shortcuts.put(k,new Shortcut(prevouter,prevouter+k+1));
            
            
            i++;
         }
         
         ArrayList<Integer> sums = new ArrayList<Integer>();
         ArrayList<Integer> psums = new ArrayList<Integer>();
         psums.add(0);
         for(int k = biggest; k >= 0; k-=8){
            sums.add(k);
            if(psums.size() == 0) psums.add(k);
            else psums.add(k + psums.get(psums.size()-1));
         }
         
         if(m%2 != 0 || target > psums.get(psums.size()-1) || target < 0){
            out.println("Case #" + q + ": IMPOSSIBLE");
            continue;
         }
         
         ArrayList<Shortcut> answer = new ArrayList<Shortcut>();
         
         for(int k = 0; k < sums.size(); k++){
            if(target > sums.get(k)){
               answer.add(shortcuts.get(sums.get(k)));
               target -= sums.get(k);
            } else {
               answer.add(shortcuts.get(target));
               break;
            }
            
         }
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add("Case #" + q + ": " + answer.size());
         for(Shortcut sc : answer){
            sj.add(sc.toString());
         }
         out.println(sj.toString());
         
         

      }
      
      
      
      
      out.close();
   }
   
   public static class Shortcut{
      int a;
      int b;
      public Shortcut(int c, int d){
         a = c;
         b = d;
      }
      public String toString(){
         return a + " " + b;
      }
   }
      
}