#include <bits/stdc++.h>

using namespace std;


string rep(int x){
   string s = "";
   for(int k = 0; k < x; k++) s += " ";
   return s;
}

string rep2(int total, int x){
   return rep(total - to_string(x).length());
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   while(true){
      int n;
      cin >> n;
      if(n == 0) break;
      
      //[first name, last name, bib #, split 1, split2, final]
      vector<vector<string>> entries(n,vector<string>(6,""));
      
      unordered_map<string,int> bibind;
      
      for(int k = 0; k < n; k++){
         cin >> entries[k][0] >> entries[k][1] >> entries[k][2];
         bibind[entries[k][2]] = k;
      }
      
      for(int k = 0; k < 3*n; k++){
         string bib, which, time;
         cin >> bib >> which >> time;
         
         int i = bibind[bib];
         int w = -1;
         if(which == "S1") w = 3;
         if(which == "S2") w = 4;
         if(which == "F") w = 5;
         
         entries[i][w] = time;
      }
      
      //[s1 rank, s2 rank, f rank]
      vector<vector<int>> ranks (n,vector<int>(3));
      for(int k = 0; k < 3; k++){
         vector<pair<string,int>> pairs(n);
         for(int j = 0; j < n; j++){
            pairs[j] = make_pair(entries[j][k+3],j);
         }
         
         sort(pairs.begin(),pairs.end());
         
         for(int j = 0; j < n; j++){
            //cout << pairs[j].first << " " << pairs[j].second << endl;
            if(j == 0){
               ranks[pairs[j].second][k] = 1;
            } else {
               if(pairs[j].first == pairs[j-1].first){
                  ranks[pairs[j].second][k] = ranks[pairs[j-1].second][k];
               } else {
                  ranks[pairs[j].second][k] = j+1;
               }
            }
         }   
      }
      
      vector<string> answer(n);
      for(int k = 0; k < n; k++){
         string s = entries[k][1] + ", " + entries[k][0];
         s = s + rep(20-s.length())
               + "     "
               + entries[k][2]
               + "     "
               + entries[k][3]
               + rep2(10,ranks[k][0])
               + to_string(ranks[k][0])
               + "     "
               + entries[k][4]
               + rep2(10,ranks[k][1])
               + to_string(ranks[k][1])
               + "     "
               + entries[k][5]
               + rep2(10,ranks[k][2])
               + to_string(ranks[k][2]);
         answer[k] = s;         
      }
      
      sort(answer.begin(),answer.end());
      
      cout << "NAME                       BIB    SPLIT1      RANK    SPLIT2      RANK    FINISH      RANK\n";
      for(int k = 0; k < n; k++){
         cout << answer[k] << "\n";
      }
      cout << "\n";
      
   }
   
   
   return 0;
}