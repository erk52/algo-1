
import numpy as np
import matplotlib.pyplot as plt
%matplotlib inline
import random
class percolation:
    
    def __init__(self, n):
        if n <=0:
            raise ValueError('Grid size must be > 0')
        self.n = n
        self.open_spaces = [0 for i in range(n**2)]
        self.full_spaces = [0 for i in range(n**2)]
        self.TOP = n**2
        self.BOTTOM = n**2 + 1
        
        self.union_set = unionFind(self.BOTTOM+1)
        for col in range(1, n+1):
            #connect top sites to top and bottom sites to bottom
            top_ind = 0 + (col-1)
            bot_ind = (n-1)*n + (col-1)
            self.union_set.union(self.TOP, top_ind)
            self.union_set.union(self.BOTTOM, bot_ind)
        
        
    
    def make_open(self, row, col):
        if not self._is_legal(row, col): raise ValueError('Illegal row or column')
            
        index = (row-1)*self.n + (col-1)
        self.open_spaces[index] = 1
        
        for new_row, new_col in [(row, col+1), (row, col-1), (row+1, col), (row-1, col)]:
            if self._is_legal(new_row, new_col) and self.is_open(new_row, new_col):
                new_index = (new_row-1)*self.n + (new_col-1)
                self.union_set.union(index, new_index)
        
    
    def is_open(self, row, col):
        if not self._is_legal(row, col): raise ValueError('Illegal row or column')
        index = (row-1)*self.n + (col-1)
        return self.open_spaces[index] == 1
        
    
    def is_full(self, row, col):
        if not self._is_legal(row, col): raise ValueError('Illegal row or column')
        index = (row-1)*self.n + (col-1)
        return self.full_spaces[index] == 1
    
    def number_open_sites(self):
        return sum(self.open_spaces)
    
    def percolates(self):
        return self.union_set.is_connected(self.BOTTOM, self.TOP)
    
    def _is_legal(self, row, col):
        if row < 1 or col < 1 or row >= self.n+1 or col >= self.n+1:
            return False
        return True
    
    def show_open_spaces(self):
        O = np.array(self.open_spaces).reshape(self.n, self.n)
        plt.pcolor(O)
    def show_full_spaces(self):
        O = np.array(self.full_spaces).reshape(self.n, self.n)
        plt.pcolor(O)
        
        
class unionFind:
    def __init__(self, n):
        self.id_arr = list(range(n))
        self.size_arr = [1 for i in range(n)]
    
    def root(self, i):
        while self.id_arr[i] != i:
            self.id_arr[i] = self.id_arr[self.id_arr[i]]
            i = self.id_arr[i]
        return i
    
    def is_connected(self, p, q):
        return self.root(p) == self.root(q)
    
    def union(self, p, q):
        i = self.root(p)
        j = self.root(q)
        if i==j:
            return None
        if (self.size_arr[i] > self.size_arr[j]):
            self.id_arr[j] = i
            self.size_arr[i] += self.size_arr[j]
        else:
            self.id_arr[i] = j
            self.size_arr[j] += self.size_arr[i]
            

def perkStats(n, trials):
  res = []
  N = n
  for t in range(trials):
      mygrid = percolation(N)
      it, maxit = 0,N**2+1
      choices = [i for i in range(N**2) if mygrid.open_spaces[i]==0]
      while not mygrid.percolates() and it < maxit:
          ind = random.choice(choices)
          choices.remove(ind)
          row = 1 + ind // N
          col = 1+ind % N

          #print(ind, row, col)
          mygrid.make_open(row,col)
          it += 1
      if not mygrid.percolates():
          break
      res.append(it/N**2)
      if t % 10 == 0:
          print(t)
  return sum(res) / len(res)
