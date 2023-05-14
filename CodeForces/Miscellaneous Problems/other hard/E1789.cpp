#include <bits/stdc++.h>
//Serval and Music Game
using namespace std;

const long long MOD = 998244353LL;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> vec(n);
      for(int k = 0; k < n; k++){
         cin >> vec[k];
      }
      
      //cout << "done input" << endl;
      
      int sn = vec[n-1];
      
      vector<int> numbelow(sn+1,0);
      for(int k = 0; k < n; k++) numbelow[vec[k]] = 1;
      
      //all values of sn/x where sn%x != 0 and the multiplier
      vector<long long> mult(sn+1,0LL);
      unordered_set<int> mults;
      //all values of sn/x where sn%x == 0
      vector<int> divs;                               //size O(sqrt(sn))
      for(int k = 1; k <= sn; k++){                   //x = sn/k
         int div = sn/k;
         if(sn%k == 0) divs.push_back(div);
         else{
            mult[div] = (mult[div] + k + MOD)%MOD;
            mults.insert(div);
         }
      }
      
      //cout << "done gen mult" << endl;
      
      //check the divisors
      long long answer = 0L;
      for(int x : divs){
         for(int k = x; k <= sn; k += x){
            if(numbelow[k] == 1){
               answer = (answer + sn/x + MOD)%MOD;
            }
         }
         //cout << x << ": " << answer << endl;
      }
      
      //cout << "done check divisors" << endl;
      
      //int loop = 0;
      //cout << divs.size() << endl;
      //cout << mult.size() << endl;
      
      //calc psum
      for(int k = 1; k <= sn; k++) numbelow[k] += numbelow[k-1];
      
      for(const auto& snx : mults){
         //long long snx = p.first;          //floor(sn/x)
         long long multiplier = mult[snx];
         
         //cout << snx << " " << multiplier << endl;
         
         long long cur = (long long)n;
         //subtract the parts that don't belong, aka # of si such that si%snx >= si/snx
         
         //end of interval
         int i = 0L;    //value of si/snx
         while(true){
            int L = snx*i + i + 1;
            int R = snx*(i+1)-1;
            //cout << snx << ": " << L << "-" << R << endl;
            if(L > sn || R < L) break;
            
            //# of si < L
            int ltL = (L == 0) ? 0 : numbelow[L-1];
            
            //# of si <= R
            int leR = numbelow[min(sn,R)];
            //cout << ltL << "->" << leR << endl;
            cur -= (leR-ltL);
            
            i++;
         }
         
         answer = (answer + cur * multiplier + MOD)%MOD;
         
      }
      
      //cout << loop << endl;
      
      
      cout << answer << endl;
      
      
      
      
   }
      
   
   
   return 0;
}