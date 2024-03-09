#include <bits/stdc++.h>
//not done
using namespace std;


template <typename T>
struct FenwickTree
{
   ll n;
   vector<T> tree;

   FenwickTree() {}

   FenwickTree(ll _n) { init(_n); }

   void init(ll _n)
   {
      n = _n;
      tree.assign(n + 1, 0);
   }

   void build(const vector<T> &a)
   {
      for (ll i = 0; i < a.size(); i++)
      {
         increment(i, a[i]);
      }
   }

   void increment(ll pos, T val)
   {
      for (; pos < n; pos = pos | (pos + 1))
      {
         tree[pos] += val;
      }
   }

   T query(ll pos)
   {
      T res = 0;
   
      for (ll i = pos; i >= 0; i = (i & (i + 1)) - 1)
         res += tree[i];
   
      return res;
   }

   T query(ll l, ll r)
   {
      return query(r) - query(l - 1);
   }
};

template <typename T>
struct FenwickTreeRangeQuery
{
   ll n;
   FenwickTree<T> ft1, ft2;

   FenwickTreeRangeQuery() {}

   FenwickTreeRangeQuery(ll _n) { init(_n); }

   void init(ll _n)
   {
      n = _n;
      ft1.init(n);
      ft2.init(n);
   }

   void rangeAdd(int l, int r, T val)
   {
      ft1.increment(l, val);
      ft1.increment(r + 1, -val);
      ft2.increment(l, val * (l - 1));
      ft2.increment(r + 1, val * (-r));
   }

   T queryHelper(int index)
   {
      return (ft1.query(index) * index - ft2.query(index));
   }

   T rangeQuery(int l, int r)
   {
      return (queryHelper(r) - queryHelper(l - 1));
   }
};

pair<int,int> gen_endpoints(int n, int m, int i){
   int l = i-m+1;
   int r = i;
   return make_pair(max(0,l),min(r,n-1));
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int N = 100005;
   vector<bool> isprime(N,true);
   for(int k = 2; k < N; k++){
      if(!isprime[k]) 
         continue;
      for(int j = 2*k; j < N; j += k){
         isprime[j] = false;
      }
   }
   
   vector<int> primes;
   for(int k = 2; k < N; k++){
      if(!isprime[k]) 
         continue;
      primes.push_back(k);
   }
   int P = primes.size();
   
   vector<int> pi(N,-1);
   for(int k = 0; k < P; k++){
      pi[primes[k]] = k;
   }
   
   int n,m,q;
   cin >> n >> m >> q;
   
   vector<FenwickTreeRangeQuery<int>> fenwicks(P);
   for(int k = 0; k < P; k++){
      FenwickTreeRangeQuery<int> ft;
      ft.init(n-m+1);
      fenwicks[k] = ft;
   }
      
   
   vector<long long> array(n);
   long long tsum = 0LL;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      sum += array[k];
      
      int i = (int)array[k];
      
      auto [l,r] = gen_endpoints(n,m,k);
      for(int p = 0; p < P; p++){
         if(i % p == 0){
            fenwick[p].rangeAdd(l,r,1);
         }
      }
   }
   
   for(int t = 0; t < q; t++){
      int i,x;
      cin >> i >> x, i--;
      long long xl = (long long)x;
      tsum -= array[i];
      tsum += x;
      array[i] = x;
      
      
   }
   
   
   return 0;
}