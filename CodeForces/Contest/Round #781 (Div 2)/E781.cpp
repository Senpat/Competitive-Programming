#include <bits/stdc++.h>
//https://cp-algorithms.com/data_structures/sqrt_decomposition.html -> for mo's algorithm
using namespace std;


int block_size = 300;            //sqrt(100000) is 316

struct Query {
   int l, r, idx;
   bool operator<(Query other) const
   {
      return make_pair(l / block_size, r) <
               make_pair(other.l / block_size, other.r);
   }
};

struct Trie{
    Trie* left;
    Trie* right;
    int size;

};

Trie* trie;

Trie* createtrie(){
   Trie* ret = new Trie();
   ret->size = 0;
   ret->left = NULL;
   ret->right = NULL;
   return ret;
}

void add(int x){
   //cout << "add " << x << endl;
   int i = 1 << 30;
   
   Trie* curtrie = trie;
   
   while(i >= 0){
      curtrie->size++;
      if(i == 0) break;
      if((x & i) == 0){
         //put in left 
         //cout << i << " left" << endl;
         if(curtrie->left == NULL){
            curtrie->left = createtrie();
         }
         curtrie = curtrie->left;
      } else {
         //put in right
         //cout << i << " right" << endl;
         if(curtrie->right == NULL){
            curtrie->right = createtrie();
         }
         curtrie = curtrie->right;
      }     
      
      
      i >>= 1;
   }
}

void remove(int x){
   //cout << "remove " << x << endl;
   int i = 1 << 30;
   
   Trie* curtrie = trie;
   
   while(i >= 0){
      curtrie->size--;
      if(i == 0) break;
      if((x & i) == 0){
         //put in left 
         /*
         if(curtrie->left == nullptr){
            Trie newtrie;
            curtrie->left = newtrie;
         }*/
         curtrie = curtrie->left;
      } else {
         //put in right
         /*
         if(curtrie->right == nullptr){
            Trie newtrie;
            curtrie->right = newtrie;
         }*/
         curtrie = curtrie->right;
      }     
      
      
      i >>= 1;
   }
}


int get_answer(){
   int i = 1 << 30;
   int answer = 0;
   
   vector<Trie*> curtries;
   curtries.push_back(trie);
   while(i > 0){
      int num0 = 0;
      for(Trie* tptr : curtries){
         /*
         cout << tptr->size << " ";
         if(tptr->left != NULL) cout << tptr->left->size << " ";
         else cout << "0 ";
         if(tptr->right != NULL) cout << tptr->right->size << " ";
         else cout << "0 ";
         cout << endl;
         */
         if(tptr->left != NULL){
            num0 += tptr->left->size;
         }
      }
      vector<Trie*> next;
      if(num0 < 2){
         //bit is 1
         answer |= i;
         for(Trie* tptr : curtries){
            if(tptr->left != NULL && tptr->left->size > 0){
               next.push_back(tptr->left);
            }
            if(tptr->right != NULL && tptr->right->size > 0){
               next.push_back(tptr->right);
            }
         }
      } else {
         //bit is 0
         for(Trie* tptr : curtries){
            if(tptr->left != NULL && tptr->left->size > 0){
               next.push_back(tptr->left);
            }
         }
      }
      
      curtries = next;
   
      i >>= 1;
   }
   
   return answer;
   

}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 1; qq <= t; qq++){
      int n;
      cin >> n;
      vector<int> array;
      for(int k = 0; k < n; k++){
         int i;
         cin >> i;
         array.push_back(i);
      }
      int q;
      cin >> q;
      vector<Query> queries;
      for(int k = 0; k < q; k++){
         //im bad at c++
         int l,r;
         cin >> l >> r;
         Query query;
         query.l = l-1;
         query.r = r-1;
         query.idx = k;
         queries.push_back(query);
      }
      
      vector<int> answer(q);
      sort(queries.begin(),queries.end());
      
      trie = createtrie();
      
      int cur_l = 0;
      int cur_r = -1;
    // invariant: data structure will always reflect the range [cur_l, cur_r]
      for (Query q : queries) {
         while (cur_l > q.l) {
            cur_l--;
            add(array[cur_l]);
         }
         while (cur_r < q.r) {
            cur_r++;
            add(array[cur_r]);
         }
         while (cur_l < q.l) {
            remove(array[cur_l]);
            cur_l++;
         }
         while (cur_r > q.r) {
            remove(array[cur_r]);
            cur_r--;
         }
         answer[q.idx] = get_answer();
      }
      
      for(int k = 0; k < q; k++){
         cout << answer[k] << endl;
      }
   
   }
   
   
   
   
   return 0;
}