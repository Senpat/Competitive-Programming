#include <bits/stdc++.h>

using namespace std;


vector<vector<int>> adj;

vector<bool> seen;

void dfscomp(int v){
   seen[v] = true;
   for(int nei : adj[v]){
      if(seen[nei]) continue;
      dfscomp(nei);
   }
}

vector<int> color;

void dfs(int v, int mask){
   seen[v] = true;
   
   for(int nei : adj[v]){
      if(seen[nei]) continue;
      
      //calculate color for nei
      if(((mask>>nei)&1) == 1){
         color[nei] = (color[v] == 2) ? 1 : 2;
      } else {
         color[nei] = (color[v] == 0) ? 1 : 0;
      }
      
      dfs(nei,mask);
   }
   
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<bool> lettersseen(26,false);
   vector<char> letters;
   vector<vector<char>> input(n,vector<char>(3));
   bool fail = false;
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < 3; j++){
         input[k][j] = s[j];
         
         int ci = s[j]-'a';
         if(!lettersseen[ci]){
            lettersseen[ci] = true;
            letters.push_back(s[j]);
         }
      }
      
      if(s[0] == s[1] || s[0] == s[2] || s[1] == s[2]){
         fail = true;
      }
   }
   
   queue<int> notused;
   for(int k = 0; k < 26; k++){
      if(!lettersseen[k]){
         notused.push(k);
      }
   }
   
   int nl = letters.size();
   if(nl > 18 || fail){
      cout << "0\n";
      return 0;
   }
   
   sort(letters.begin(),letters.end());
   
   vector<int> ctoi(26);
   for(int k = 0; k < nl; k++){
      ctoi[letters[k]-'a'] = k;
   }
   
   vector<vector<int>> array(n,vector<int>(3));
   for(int k = 0; k < n; k++){
      for(int j = 0; j < 3; j++){
         array[k][j] = ctoi[input[k][j]-'a'];
      }
   }
   
   adj = vector<vector<int>>(nl,vector<int>());
   for(int k = 0; k < n; k++){
      for(int a = 0; a < 3; a++) for(int b = a+1; b < 3; b++){
         adj[array[k][a]].push_back(array[k][b]);
         adj[array[k][b]].push_back(array[k][a]);
      }
   }
   
   //get # of components
   seen = vector<bool>(nl,false);
   int comp = 0;
   for(int k = 0; k < nl; k++){
      if(!seen[k]){
         comp++;
         dfscomp(k);
      }
   }
   
   int pcomp = 1;
   for(int k = 0; k < comp; k++) pcomp *= 3;
   
   int pnl = (1 << nl);
   
   bool found = false;
   //loop over combinations
   for(int compmask = 0; compmask < pcomp; compmask++){
      if(found) break;
      
      vector<int> compcolors(comp);
      int compi = compmask;
      for(int k = 0; k < comp; k++){
         compcolors[k] = compi%3;
         compi /= 3;
      }
      
      for(int mask = 0; mask < pnl; mask++){
         //see if this combination results in a valid coloring of die
         
         color = vector<int>(nl);
         seen = vector<bool>(nl,false);
         
         int curcompi = 0;
         for(int k = 0; k < nl; k++){
            if(!seen[k]){
               color[k] = compcolors[curcompi];
               
               dfs(k,mask);
               
               curcompi++;
            }
         }
         //cout << "after dfs" << endl;
         //check colors
         bool curfail = false;
         for(int v = 0; v < nl; v++){
            for(int nei : adj[v]){
               if(color[v] == color[nei]){
                  curfail = true;
                  break;
               }
            }
            if(curfail) break;
         }
         
         if(curfail) continue;
         
         vector<vector<char>> each(3,vector<char>());
         for(int k = 0; k < nl; k++){
            each[color[k]].push_back(letters[k]);
            if(each[color[k]].size() > 6){
               curfail = true;
            }
         }
         
         if(curfail) continue;
         
         //print solution
         for(int k = 0; k < 3; k++){
            while(each[k].size() < 6){
               each[k].push_back(notused.front() + 'a');
               notused.pop();
            }
            
            for(int j = 0; j < 6; j++){
               cout << each[k][j];
            }
            cout << " ";
         }
         
         found = true;
         break;
         
         
         
      }
      
      
   }
   
      
   if(!found){
      cout << "0\n";
   }
   
   
   return 0;
}