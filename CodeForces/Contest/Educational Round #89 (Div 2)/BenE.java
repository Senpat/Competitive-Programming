/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

******************************************************************************/

import java.util.*;
import java.io.*;
public class BenE
{
    static long[] a;
    static long[] b;
    static long[] last;
    static long[] first;
    static long mod = 998244353;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        a = new long[n];
        b = new long[m];
        first = new long[m];
        last = new long[m];
        //Arrays.setAll(appear, i -> new ArrayList<Integer>());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }
        int j = m-1;
        boolean works = true;
        for(int i = n-1; i >= 0; i--){
            if(a[i] < b[Math.max(0,j)]){
                works = false;
                break;
            }
            if(j >= 0 && a[i] == b[j]){
                last[j] = i;
                j--;
            }
        }
        if(j != -1)
            works = false;
        if(!works){
            out.println(0);
        }
        else{
            j = 0;
            for(int i = 0; i < n && j < m; i++){
                if(a[i] == b[j]){
                    first[j] = i;
                    j++;
                }
            }
            out.println(solve(0,0));
        }
        out.close();
	}
	static long solve(int astart, int bstart){
	    if(bstart == b.length){
	        if(astart < a.length){
	            for(int i = astart; i < a.length; i++){
	                if(a[i] < b[bstart-1])
	                    return 0;
	            }
	        }
	        return 1;
	    }
	    if(astart == a.length)
	        return 0;
	    long ans = 0L;
	    long prev = astart-1;
	    if(bstart == 0)
	        prev = first[0] - 1;
	    for(int i = astart; i <= last[bstart]; i++){
	        if(a[i] == b[bstart]){
	            long temp = solve(i+1,bstart+1);
	            ans = (ans + (temp*(i - prev)) + mod)%mod;
	            if(temp != 0)
    	           prev = i;
	        }
	        if(a[i] < b[bstart] || astart == 0)
	            prev = i;
	        if(bstart > 0 && a[i] <= b[bstart-1])
	            return 0L;
	    }
	    return ans;
	}
}
