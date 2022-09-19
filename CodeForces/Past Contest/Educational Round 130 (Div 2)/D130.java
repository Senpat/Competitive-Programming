//make sure to make new file!
import java.io.*;
import java.util.*;

public class D130{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      ArrayList<Pos> pos = new ArrayList<Pos>();
      int[] posi = new int[26];
      
      char[] answer = new char[n];
      char q1 = query(0);
      answer[0] = q1;
      posi[q1-'a'] = 0;
      pos.add(new Pos(q1,0));
      
      for(int k = 1; k < n; k++){
         int l = 0;
         int r = pos.size()-1;
         char ans = '?';
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            int ret = query(pos.get(mid).i,k);
            if(ret == pos.size()-mid+1){
               r = mid-1;
            } else {
               ans = pos.get(mid).ch;
               l = mid+1;
            }
            
         }
         
         if(ans == '?'){
            answer[k] = query(k);
            pos.add(new Pos(answer[k],k));
         } else {
            answer[k] = ans;
            int i = -1;
            for(int j = 0; j < pos.size(); j++){
               if(pos.get(j).ch == ans){
                  i = j;
                  break;
               }
            }
            pos.remove(i);
            pos.add(new Pos(answer[k],k));
            
         }
         
      
      }
      
      out.print("! ");
      for(int k = 0; k < n; k++){
         out.print(answer[k]);
      }
      out.println();
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pos{
      char ch;
      int i;
      public Pos(char a, int b){
         ch = a;
         i = b;
      }
      public String toString(){
         return ch + " " + i;
      }
   }
   
   public static char query(int i)throws IOException{
      out.println("? 1 " + (i+1));
      out.flush();
      
      return f.readLine().charAt(0);
   }
   
   public static int query(int l, int r)throws IOException{
      out.println("? 2 " + (l+1) + " " + (r+1));
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
}