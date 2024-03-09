#include <bits/stdc++.h>
//O(nlogn) with cht
using namespace std;

//Line Container template
//insert lines y = k*x + m, query max value at point x
//to get min value, insert -k and -m and get negative of query output.
struct Line {
	mutable long long k, m, p;
	bool operator<(const Line& o) const { return k < o.k; }
	bool operator<(long long x) const { return p < x; }
};

struct LineContainer : multiset<Line, less<>> {
	// (for doubles, use inf = 1/.0, div(a,b) = a/b)
	static const long long inf = LLONG_MAX;
	long long div(long long a, long long b) { // floored division
		return a / b - ((a ^ b) < 0 && a % b); }
	bool isect(iterator x, iterator y) {
		if (y == end()) return x->p = inf, 0;
		if (x->k == y->k) x->p = x->m > y->m ? inf : -inf;
		else x->p = div(y->m - x->m, x->k - y->k);
		return x->p >= y->p;
	}
   //y = k*x + m
	void add(long long k, long long m) {
		auto z = insert({k, m, 0}), y = z++, x = y;
		while (isect(y, z)) z = erase(z);
		if (x != begin() && isect(--x, y)) isect(x, y = erase(y));
		while ((y = x) != begin() && (--x)->p >= y->p)
			isect(x, erase(y));
	}
	long long query(long long x) {
		assert(!empty());
		auto l = *lower_bound(x);
		return l.k * x + l.m;
	}
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<int> array(n+1);
      vector<long long> freq(n+5,0LL);
      for(int k = 1; k <= n; k++){
         cin >> array[k];
         if(array[k] <= n){
            freq[array[k]]++;
         }
      }
      
      int mex = 0;
      while(freq[mex] > 0) mex++;
      
      vector<long long> dp(mex+1,0LL);       //dp[x] stores min to make that value the mex
      
      LineContainer lc;
      
      //dp[mex] = 0;
      lc.add(-(long long)mex, 0LL);
      
      for(int k = mex-1; k >= 0; k--){
         dp[k] = (long long)k + -lc.query(freq[k]-1LL);
         lc.add(-(long long)k,-dp[k]);
      }
      
      cout << dp[0] << endl;
      
      
   }
   
   return 0;
}