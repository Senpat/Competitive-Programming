#include <bits/stdc++.h>

using namespace std;

struct State{
   int len;
   int suffix_link;
   int transition[26];
   
   long long ends;         //how many ending positions are in this state
   long long substrings;
   State(int length, long long end) : len(length), suffix_link(-1), ends(end), substrings(0LL){
      for(int k = 0; k < 26; k++){
         transition[k] = -1;
      }
      //cout << "init " << suffix_link << endl;
   }
};

vector<State> sa;

int extend_sa(int last, char c){
   int chi = c-'a';
   int cur = sa.size();
   //cout << "create " << cur << endl;
   sa.push_back(State(sa[last].len+1,1LL));
   
   //cout << last.len << " " << last.suffix_link << " " << last.transition[chi] << endl;
   int p = last;    //last
   
   while(p != -1 && sa[p].transition[chi] == -1){
      //cout << chi << " " << p << endl;
      sa[p].transition[chi] = cur;
      p = sa[p].suffix_link;
   }
   //cout << p << endl;
   if(p == -1){
      sa[cur].suffix_link = 0;
      //cout << cur.suffix_link << " " << sa[1].suffix_link << endl;
      sa[0].transition[chi] = cur;
   } else {
      int q = sa[p].transition[chi];
      if(sa[p].len + 1 == sa[q].len){
         sa[cur].suffix_link = q;
      } else {
         //clone
         int clone = sa.size();
         //cout << "create clone " << clone << endl;
         sa.push_back(State(sa[p].len+1,0LL));
         
         sa[clone].suffix_link = sa[q].suffix_link;
         sa[q].suffix_link = clone;
         for(int k = 0; k < 26; k++){
            sa[clone].transition[k] = sa[q].transition[k];
         }
         sa[cur].suffix_link = clone;
         
         while(p != -1 && sa[p].transition[chi] == q){
            sa[p].transition[chi] = clone;
            p = sa[p].suffix_link;
         }
      }
   }
   
   return cur;
   
   
}

vector<vector<int>> suffadj;

void suffdfs(int v){
   //cout << "suff " << v << endl;
   for(int nei : suffadj[v]){
      suffdfs(nei);
      sa[v].ends += sa[nei].ends;
   }
}

void dfs(int v){
   if(sa[v].substrings != 0LL){
      //cout << "dfs 0 " << v << endl;
      return;
   }
   //cout << "dfs " << v << endl;
   for(int k = 0; k < 26; k++){
      if(sa[v].transition[k] != -1){
         dfs(sa[v].transition[k]);
      }
   }
   
   for(int k = 0; k < 26; k++){
      if(sa[v].transition[k] != -1){
         sa[v].substrings += sa[sa[v].transition[k]].substrings;
      }
   }
   sa[v].substrings += sa[v].ends;
}

string answer;

void getmth(int si, long long m){
   if(m <= sa[si].ends) return;
   //cout << si << " " << m << " " << sa[si].ends << " " << sa[si].substrings << endl;
   m -= sa[si].ends;
   
   for(int k = 0; k < 26; k++){
      if(sa[si].transition[k] == -1) continue;
      if(sa[sa[si].transition[k]].substrings >= m){
         answer += 'a'+k;
         getmth(sa[si].transition[k],m);
         return;
      }
      
      m -= sa[sa[si].transition[k]].substrings;
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   long long m;
   cin >> s >> m;
   
   int n = s.length();
   
   sa = vector<State>();
   sa.push_back({0,0LL});
   int last = 0;
   for(int k = 0; k < n; k++){
      //cout << k << endl;
      last = extend_sa(last,s[k]);
   }
   
   //make suffix link tree
   suffadj = vector<vector<int>>(sa.size(),vector<int>());
   for(int k = 1; k < sa.size(); k++){
      //cout << k << " " << sa[k].suffix_link << endl;
      suffadj[sa[k].suffix_link].push_back(k);
   }
   
   suffdfs(0);
   sa[0].ends = 0LL;
   dfs(0);
   /*
   for(int k = 0; k < sa.size(); k++){
      cout << k << " " << sa[k].ends << " " << sa[k].substrings << endl;
   }
   */
   answer = "";
   getmth(0,m);
   
   cout << answer << endl;
   
   
   return 0;
}