#include <bits/stdc++.h>

using namespace std;

//https://github.com/ucf-programming-team/hackpack-cpp/blob/master/content/graphs/DominatorTree.h
/**
 * Description: Given a digraph, return the edges of the dominator tree given as an adj. list (directed tree downwards from the root)
 * Time: $O((n + m) * log n)$ where n is the number of verticies in the graph and m is the number of edges
 * Status: Tested
 */
vector<vector<int>> dominator_tree(const vector<vector<int>>& adj, int root) {
	int n = adj.size() + 1, co = 0;
	vector<vector<int>> ans(n), radj(n), child(n), sdomChild(n);
	vector<int> label(n), rlabel(n), sdom(n), dom(n), par(n), bes(n);
	auto get = [&](auto self, int x) -> int {
		if (par[x] != x) {
			int t = self(self, par[x]);
			par[x] = par[par[x]];
			if (sdom[t] < sdom[bes[x]]) bes[x] = t;
		}
		return bes[x];
	};
	auto dfs = [&](auto self, int x) -> void {
		label[x] = ++co, rlabel[co] = x;
		sdom[co] = par[co] = bes[co] = co;
		for (auto y : adj[x]) {
			if (!label[y])
				self(self, y), child[label[x]].push_back(label[y]);
			radj[label[y]].push_back(label[x]);
		}
	};
	dfs(dfs, root);
	for (int i = co; i >= 1; --i) {
		for (auto j : radj[i])
			sdom[i] = min(sdom[i], sdom[get(get, j)]);
		if (i > 1) sdomChild[sdom[i]].push_back(i);
		for (auto j : sdomChild[i]) {
			int k = get(get, j);
			if (sdom[j] == sdom[k]) dom[j] = sdom[j];
			else dom[j] = k;
		}
		for (auto j : child[i]) par[j] = i;
	}
	for (int i = 2; i < co + 1; ++i) {
		if (dom[i] != sdom[i]) dom[i] = dom[dom[i]];
		ans[rlabel[dom[i]]].push_back(rlabel[i]);
	}
	return ans;
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   
   return 0;
}