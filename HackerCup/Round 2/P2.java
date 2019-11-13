import java.io.*;
import java.util.*;
//BY P2 I MEAN P1
class P2{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("p2.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p2.out")));
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int agents = Integer.parseInt(st.nextToken());
         
         
         
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         
         if(agents == 1){
            out.println("Case #" + q + ": N");
            continue;
         }
         
         st = new StringTokenizer(f.readLine());
      
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         
         if(mdis(a,b,x1,y1) % 2 == 0 && mdis(a,b,x2,y2) % 2 == 0){
            out.println("Case #" + q + ": Y");
         } else {
            out.println("Case #" + q + ": N");
         }
         
         
      }
        
        
      out.close();
   }
   
   public static int mdis(int a, int b, int x, int y){
      return (x-a) + (y-b);
   }
      
}