//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      boolean[][][] times = new boolean[24][60][60];
      int starts = 0;
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         String hs = st.nextToken();
         String ms = st.nextToken();
         String ss = st.nextToken();
         
         HashSet<Integer> hhs = geths(hs,24);
         HashSet<Integer> mhs = geths(ms,60);
         HashSet<Integer> shs = geths(ss,60);
         
         
         for(int h = 0; h < 24; h++){
            if(!hhs.contains(h)) 
               continue;
            for(int m = 0; m < 60; m++){
               if(!mhs.contains(m)) 
                  continue;
               for(int s = 0; s < 60; s++){
                  if(!shs.contains(s)) 
                     continue;
                  times[h][m][s] = true;
                  starts++;
               }
            }
         }
      }
      
      int timenum = 0;
      for(int h = 0; h < 24; h++){
         for(int m = 0; m < 60; m++){
            for(int s = 0; s < 60; s++){
               if(times[h][m][s]) timenum++;
            }
         }
      }
      
      out.println(timenum + " " + starts);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static HashSet<Integer> geths(String s, int max){
      HashSet<Integer> ret = new HashSet<Integer>();
      
      if(s.equals("*")){
         for(int k = 0; k < max; k++){
            ret.add(k);
         }
      } else {
         //split by ','
         String[] vals = s.split(",");
         for(int k = 0; k < vals.length; k++){
            if(vals[k].contains("-")){
               String[] rangesplit = vals[k].split("-");
               int l = Integer.parseInt(rangesplit[0]);
               int r = Integer.parseInt(rangesplit[1]);
               for(int j = l; j <= r; j++) ret.add(j);
            } else {
               ret.add(Integer.parseInt(vals[k]));
            }
         }
      }
      
      return ret;
   }
   
      
}