import java.io.*;
import java.util.*;

class teleport{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("teleport.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n][2];
      ArrayList<Integer> alist = new ArrayList<Integer>();
      
      long closesum = 0L;
      long farsum = 0L;
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         matrix[k][0] = a;
         matrix[k][1] = b;
         if(Math.min(Math.abs(a),Math.abs(b))<Math.abs(a-b)){
            if(Math.min(Math.abs(a),Math.abs(b)) == Math.abs(a)){
               alist.add(b);
               farsum += b;
            } else {
               alist.add(a);
               farsum += a;
            }
         }
      }
      
      double average = 0.0;
      for(int k = 0; k < alist.size(); k++){
         average+=alist.get(k);
      }
      average /= alist.size();
      System.out.println(average);
      double sum = 0.0;
      for(int k = 0; k < matrix.length; k++){
         //System.out.println(Math.min(calcno(matrix[k][0],matrix[k][1],average),calcyes(matrix[k][0],matrix[k][1],average)));
         sum+=Math.min(calcno(matrix[k][0],matrix[k][1],average),calcyes(matrix[k][0],matrix[k][1],average));
      }
      System.out.println(sum);
      out.println(String.format("%.0f", sum));
      out.close();
      
   }
   
   public static double calcno(int x, int y, double t2){
      return (double)Math.abs(x-y);
   }
   
   public static double calcyes(int x, int y, double t2){
      if(Math.abs(x) > Math.abs(y))
         return (double)(Math.abs(y) + Math.abs(x-t2));
      return (double)(Math.abs(x) + Math.abs(y-t2));
   }
}
      
      
      