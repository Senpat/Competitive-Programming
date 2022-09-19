#include <bits/stdc++.h>

using namespace std;

int N = 120;
int T = 240;

ofstream fout ("Esimul.out");

void printboard(const vector<vector<char>>& board,const vector<vector<char>>& slimes){
   for(int k = 0; k < N; k++){
      for(int j = 0; j < N; j++){
         fout << board[k][j];
      }
      fout << "\n";
   }
   fout << "Slimes:\n";
   for(int k = 0; k < N; k++){
      for(int j = 0; j < N; j++){
         fout << slimes[k][j];
      }
      fout << "\n";
   }
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   vector<vector<char>> board(N,vector<char>(N,'-'));
   vector<vector<char>> slimes(N,vector<char>(N,'.'));
   slimes[0][0] = '*';
   
   //random point
   int x = 5;
   int y = 3;
   
   int last = 0;
   for(int t = 0; t < T; t++){
      //fout << t << "\n";
      //printboard(board,slimes);
      if(slimes[x][y] == '*'){
         cout << t-last << "\n";
         last = t;
      }
      //move
      vector<vector<char>> newslimes(N,vector<char>(N,'.'));
      for(int k = 0; k < N; k++){
         for(int j = 0; j < N; j++){
            if(slimes[k][j] == '*'){
               if(board[k][j] == '-' && j+1 < N){
                  newslimes[k][j+1] = '*';
               }
               if(board[k][j] == '|' && k+1 < N){
                  newslimes[k+1][j] = '*';
               }
               
               if(board[k][j] == '-') board[k][j] = '|';
               else board[k][j] = '-';
            }
         }
      }
      
      for(int k = 0; k < N; k++){
         for(int j = 0; j < N; j++){
            slimes[k][j] = newslimes[k][j];
         }
      }
      slimes[0][0] = '*';
   }
   
   
   return 0;
}