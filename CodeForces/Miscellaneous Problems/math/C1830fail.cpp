#include <bits/stdc++.h>
//fail, wrong sol
using namespace std;

const long long MOD = 998244353;

vector<long long> fac;
vector<long long> ifac;
vector<long long> catalan;

//from geeksforgeeks
long long modInverse(long long a, long long m) 
{ 
    long long m0 = m; 
    long long y = 0LL;
    long long x = 1LL; 
  
    if (m == 1LL) 
      return 0LL; 
  
    while (a > 1LL) 
    { 
        // q is quotient 
        long long q = a / m; 
        long long t = m; 
  
        // m is remainder now, process same as 
        // Euclid's algo 
        m = a % m;
        a = t; 
        t = y; 
  
        // Update y and x 
        y = x - q * y; 
        x = t; 
    } 
  
    // Make x positive 
    if (x < 0LL) 
       x += m0; 
    return x; 
}  

vector<int> parent;

int getp(int v){
   if(parent[v] == v) return v;
   parent[v] = getp(parent[v]);
   return parent[v];
}

//make the parent of from to to
//parent[from] should equal from, and parent[to] should equal to
int join(int from, int to){
   parent[from] = to;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 200005;
   
   fac = vector<long long>(N);
   ifac = vector<long long>(N);
   catalan = vector<long long>(N);
   
   fac[0] = 1L;
      
   for(int k = 1; k < N; k++){
      fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
   }
   
   ifac[N-1] = modInverse(fac[N-1],MOD);
   
   for(int k = N-2; k >= 0; k--){
      ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
   }
   
   for(int k = 0; k < 200001; k += 2){
      int i = k/2;
      catalan[k] = (modInverse(2*i + 1, MOD) * choose(2*i + 1,i) + MOD)%MOD;
   }
   
   int t;
   cin >> t;
   
   for(int q = 0; q < t; q++){
      
      int n,m;
      cin >> n >> m;
      
      vector<pair<int,int>> intervals(m+1);
      intervals[0] = make_pair(0,n);
      
      for(int k = 1; k <= m; k++){
         int l,r;
         cin >> l >> r, l--;
         
         intervals[k] = make_pair(l,r);
      }
      
      sort(intervals.begin(),intervals.end());
      
      //pair is endpoint, and initial component (use dsu to find current component)
      priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
      
      vector<set<int>> comp_endpoints;
      
      parent = vector<int>();
      
      for(int k = 0; k <= m; k++){
         //see if it should combine any components
         unordered_set<int> combine;
         
         //ended before
         while(!pq.empty() && pq.top().first < intervals[k].first){
            //add next one
            int comp = parent[pq.top().second];
            if(comp_endpoints[comp].size() > 0){
               int nxt = *comp_endpoints[comp].begin();
               pq.push(make_pair(nxt,comp));
               comp_endpoints[comp].erase(nxt);
            }
            pq.pop();
         }
         while(!pq.empty() && pq.top().first <= intervals[k].second){
            combine.insert(parent[pq.top().second]);
         }
      }
      
      
      
   }
   
   return 0;
}