#include <bits/stdc++.h>
//hungarian algorithm practice
using namespace std;

/*
from https://github.com/bqi343/cp-notebook/blob/master/Implementations/content/graphs%20(12)/Matching/Hungarian.h
 * Description: Given J jobs and W workers (J <= W), computes the minimum cost
 * to assign each prefix of jobs to distinct workers.
 *
 * @param C a matrix of dimensions JxW such that C[j][w] = cost to assign j-th
 * job to w-th worker (possibly negative)
 *
 * @return a vector of length J, with the j-th entry equaling the minimum cost
 * to assign the first (j+1) jobs to distinct workers
 * Time: O(J^2W)
 */

bool ckmin(long long& a, const long long& b){
   return b < a ? a=b, 1 : 0;          //set a = min(a,b)
}

vector<long long> hungarian(const vector<vector<long long>> &C) {
	const int J = (int)size(C), W = (int)size(C[0]);
	//assert(J <= W);
	/// job[w] = job assigned to w-th worker, or -1 if no job assigned
	/// note: a W-th worker was added for convenience
	vector<int> job(W + 1, -1);
	vector<long long> ys(J), yt(W + 1); /// potentials
	/// -yt[W] will equal the sum of all deltas
	vector<long long> answers;
	const long long inf = numeric_limits<long long>::max();
	for (int j_cur = 0; j_cur < J; ++j_cur) { /// assign j_cur-th job
		int w_cur = W;
		job[w_cur] = j_cur;
		/// min reduced cost over edges from Z to worker w
		vector<long long> min_to(W + 1, inf);
		vector<int> prv(W + 1, -1); /// previous worker on alternating path
		vector<bool> in_Z(W + 1);   /// whether worker is in Z
		while (job[w_cur] != -1) {  /// runs at most j_cur + 1 times
			in_Z[w_cur] = true;
			const int j = job[w_cur];
			long long delta = inf;
			int w_next;
			for (int w = 0; w < W; ++w) {
				if (!in_Z[w]) {
					if (ckmin(min_to[w], C[j][w] - ys[j] - yt[w]))
						prv[w] = w_cur;
					if (ckmin(delta, min_to[w])) w_next = w;
				}
			}
			/// delta will always be non-negative,
			/// except possibly during the first time this loop runs
			/// if any entries of C[j_cur] are negative
			for (int w = 0; w <= W; ++w) {
				if (in_Z[w]) ys[job[w]] += delta, yt[w] -= delta;
				else min_to[w] -= delta;
			}
			w_cur = w_next;
		}
		/// update assignments along alternating path
		for (int w; w_cur != -1; w_cur = w) job[w_cur] = job[w = prv[w_cur]];
		answers.push_back(-yt[W]);
	}
	return answers;
}


long long dist(const pair<int,int>& p1, const pair<int,int>& p2){
   return (long long)(abs(p1.first-p2.first) + abs(p1.second-p2.second));
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<pair<int,int>> bottles(n);
   for(int k = 0; k < n; k++){
      cin >> bottles[k].first >> bottles[k].second;
   }
   vector<pair<int,int>> couriers(m);
   for(int k = 0; k < m; k++){
      cin >> couriers[k].first >> couriers[k].second;
   }
   
   pair<int,int> rest;
   cin >> rest.first >> rest.second;
   
   vector<vector<long long>> cost(n,vector<long long>(m+n-1,0LL));
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         //courier -> bottle -> restaurant
         cost[k][j] = dist(couriers[j],bottles[k]) + dist(bottles[k],rest);
      }
      //n-1 dummy restaurants (one bottle has to come from an actual courier
      for(int j = 0; j < n-1; j++){
         cost[k][j + m] = 2LL*dist(bottles[k],rest);
      }
   }
   
   auto ret = hungarian(cost);
   cout << ret.back() << endl;
   
   
   return 0;
}