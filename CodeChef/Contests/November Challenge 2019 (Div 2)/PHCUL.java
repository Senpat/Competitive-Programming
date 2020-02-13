//make sure to make new file!
import java.io.*;
import java.util.*;

class PHCUL{
   
   public static int x,y;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         x = Integer.parseInt(st.nextToken());
         y = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         ArrayList<Point> al = new ArrayList<Point>(a);
         ArrayList<Point> bl = new ArrayList<Point>(b);
         ArrayList<Point> cl = new ArrayList<Point>(c);
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < a; k++){
            
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            
            al.add(new Point(ax,ay));
         }
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < b; k++){
            
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            
            bl.add(new Point(bx,by));
         }
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < c; k++){
            
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            
            cl.add(new Point(cx,cy));
         }
         
         double answer = Math.min(calc(al,bl,cl),calc(bl,al,cl));
         
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static double calc(ArrayList<Point> alist, ArrayList<Point> blist, ArrayList<Point> clist){
      int a = alist.size();
      int b = blist.size();
      int c = clist.size();
      
      double[] mina = new double[a];
      double[] minb = new double[b];
      double[] minc = new double[c];
         
      Arrays.fill(mina,Double.MAX_VALUE);
      Arrays.fill(minb,Double.MAX_VALUE);
      Arrays.fill(minc,Double.MAX_VALUE);
         
      for(int k = 0; k < a; k++){
         mina[k] = dist(x,y,alist.get(k).x,alist.get(k).y);
      }
         
      for(int k = 0; k < a; k++){
         for(int j = 0; j < b; j++){
            double d = dist(alist.get(k).x,alist.get(k).y,blist.get(j).x,blist.get(j).y);
            minb[j] = Math.min(minb[j],d+mina[k]);
         }
      }
         
      for(int k = 0; k < b; k++){
         for(int j = 0; j < c; j++){
            double d = dist(blist.get(k).x,blist.get(k).y,clist.get(j).x,clist.get(j).y);
            minc[j] = Math.min(minc[j],d+minb[k]);
         }
      }
         
      double answer = Double.MAX_VALUE;
      for(int k = 0; k < c; k++){
         answer = Math.min(answer,minc[k]);
      }
      
      return answer;
   }
   
   public static double dist(int x1, int y1, int x2, int y2){
      return Math.sqrt(((double)x1-(double)x2)*((double)x1-(double)x2) + ((double)y1-(double)y2)*((double)y1-(double)y2));
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
   
      
}