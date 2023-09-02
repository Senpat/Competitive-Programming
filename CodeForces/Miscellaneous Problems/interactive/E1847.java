//Triangle Platinum?
import java.io.*;
import java.util.*;
//way too long, fail implementation
public class E1847{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Integer> rhmap = new HashMap<Integer,Integer>();        //maps area to value that was repeated
      int[][][] a = new int[5][5][5];
      for(int k = 1; k <= 4; k++){
         for(int j = 1; j <= 4; j++){
            for(int h = 1; h <= 4; h++){
               if(k+j <= h || k+h <= j || j+h <= k){
                  a[k][j][h] = 0;
               } else {
                  a[k][j][h] = getarea(k,j,h);
                  if(k == j || k == h){
                     rhmap.put(a[k][j][h],k);
                  } else if(j == h){
                     rhmap.put(a[k][j][h],j);
                  }
               }
            }
         }
      }
      
      ArrayList<Quad> quads = new ArrayList<Quad>();
      //can't have a 1
      for(int k = 2; k <= 4; k++){
         for(int j = 2; j <= 4; j++){
            for(int h = 2; h <= 4; h++){
               for(int g = 2; g <= 4; g++){
                  int[] vals = new int[]{k,j,h,g};
                  int[] areas = new int[]{getarea(k,j,h),getarea(k,j,g),getarea(k,h,g),getarea(j,h,g)};
                  quads.add(new Quad(vals,areas));
               }
            }
         }
      } 
      
      HashMap<Integer,Integer> repwith1 = new HashMap<Integer,Integer>();
      for(int k = 2; k <= 4; k++){
         repwith1.put(getarea(k,k,1),k);
      }
      
      if(n == 3){
         int area = query(1,2,3);
         if(area == a[1][1][1]){
            out.println("! 1 1 1");
         } else if (area == a[2][2][2]){
            out.println("! 2 2 2");
         } else if (area == a[3][3][3]){
            out.println("! 3 3 3");
         } else if (area == a[4][4][4]){
            out.println("! 4 4 4");
         } else {
            out.println("! -1");
         }
         out.flush();
         out.close();
         return;
      }
      
      int[] answer = new int[n+1];
      
      int one = -1;
      int repval = -1;
      int rep1 = -1;
      int rep2 = -1;
      ArrayList<Integer> non1set = new ArrayList<Integer>();         //sets of 4 with a non1
      ArrayList<Integer> non1 = new ArrayList<Integer>();
      for(int k = 1; k <= n; k += 4){
         if(non1.size() + 2*non1set.size() >= 4){
            break;
         }
      
         int a012 = query(k,k+1,k+2);
         boolean found = false;
         // trivial case
         for(int j = 2; j <= 4; j++){
            if(a012 == a[j][j][j]){
               answer[k] = j;
               answer[k+1] = j;
               answer[k+2] = j;
               repval = j;
               rep1 = k;
               rep2 = k+1;
               found = true;
            }
         }
         
         if(found) 
            break;
         int a123 = query(k+1,k+2,k+3);
         // case 1: know 4 ones
         if(a012 == a[1][1][1] && a123 == a[1][1][1]){
            for(int j = 0; j <= 3; j++){
               answer[k+j] = 1;
            }
            one = k;
            continue;
         }
         
         int a013 = query(k,k+1,k+3);
         int a023 = query(k,k+2,k+3);
         
         int[] cur = new int[]{a012,a013,a023,a123};
         
         //case 2: know 3 ones and 1 non-one
         if(a012 == a[1][1][1]){
            answer[k] = 1;
            answer[k+1] = 1;
            answer[k+2] = 1;
            non1.add(k+3);
            continue;
         }
         if(a013 == a[1][1][1]){
            answer[k] = 1;
            answer[k+1] = 1;
            answer[k+3] = 1;
            non1.add(k+2);
            continue;
         }
         if(a023 == a[1][1][1]){
            answer[k] = 1;
            answer[k+2] = 1;
            answer[k+3] = 1;
            non1.add(k+1);
            continue;
         }
         if(a123 == a[1][1][1]){
            answer[k+1] = 1;
            answer[k+2] = 1;
            answer[k+3] = 1;
            non1.add(k);
            continue;
         }
         
         //case 3: all fail (2 1s and 2 different)
         if(a012 + a013 + a023 + a123 == 0){
            non1set.add(k);
            continue;
         }
         
         //case 4: 1 2 3 4
         int num0 = 0;
         int num135 = 0;      //234
         for(int j = 0; j < 4; j++){
            if(cur[j] == 0) num0++;
            if(cur[j] == 135) num135++;
         }
         if(num135 == 1 && num0 == 3){
            if(a012 == 135){
               non1.add(k);
               non1.add(k+1);
               non1.add(k+2);
               one = k+3;
            } else if(a013 == 135){
               non1.add(k);
               non1.add(k+1);
               non1.add(k+3);
               one = k+2;
            } else if(a023 == 135){
               non1.add(k);
               non1.add(k+2);
               non1.add(k+3);
               one = k+1;
            } else if(a123 == 135){
               non1.add(k+1);
               non1.add(k+2);
               non1.add(k+3);
               one = k;
            }
            continue;
         }
         
         //case 5: all 4 are non1, can calculate
         found = false;
         for(Quad q : quads){
            if(q.cur[0] == cur[0] && q.cur[1] == cur[1] && q.cur[2] == cur[2] && q.cur[3] == cur[3]){
               found = true;
               repval = q.repval;
               rep1 = q.rep1;
               rep2 = q.rep2;
               break;
            }
         }
         
         if(found) 
            break;
         
         //case 6: 1s and 2 repeats
         //index of 0
         ArrayList<Integer> io0 = new ArrayList<Integer>();
         //index of non 0
         ArrayList<Integer> ion0 = new ArrayList<Integer>();
         for(int j = 0; j < 4; j++){
            if(cur[j] == 0) io0.add(j);
            else ion0.add(j);
         }
         
         //last case, should be true
         if(io0.size() == 2){
            if(cur[(int)ion0.get(0)] == cur[(int)ion0.get(1)]){
               repval = repwith1.get(cur[(int)ion0.get(0)]);
               rep1 = k+3-ion0.get(0);
               rep2 = k+3-ion0.get(1);
               one = io0.get(0);
               continue;
            }
         }
         
         //should never reach here
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int getarea(int a, int b, int c){
      int p = a+b+c;
      return p*(p-2*a)*(p-2*b)*(p-2*c);
   }
   
   public static int query(int a, int b, int c) throws IOException{
      out.println("? " + a + " " + b + " " + c);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
   //class to store possible values for 4 consecutive values
   public static class Quad{
      int[] cur;
      int repval;
      int rep1;
      int rep2;
      
      public Quad(int[] vals, int[] areas){
         cur = areas;
         
         for(int k = 0; k < 4; k++){
            if(cur[k] == 0) 
               return;
         }
         
         for(int k = 0; k < 4; k++){
            for(int j = 1; j < 4; j++){
               if(vals[k] == vals[j]){
                  repval = vals[k];
                  rep1 = k;
                  rep2 = j;
                  return;
               }
            }
         }
            
      }
   }
}