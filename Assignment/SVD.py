'''
Created on Nov 6, 2017

@author: Lakshya
'''
import numpy as np
import scipy.linalg as SL
import matplotlib.pyplot as plt
import Assignment.tfidfusingsklearn as tfidf

'''
ex = [[1,0,1,0,0,0],
      [0,1,0,0,0,0],
      [1,1,0,0,0,0],
      [1,0,0,1,1,0],
      [0,0,0,1,0,1]]
'''
RANK = 2

U, s, Vh = SL.svd(tfidf.tfidf_matrix.toarray(), full_matrices=False)

print("\n\nSVD TF-IDF Matrix")
print(U)

print("\n\n∑ diagonal matrix")
print(s)

print("\n\nV' SVD document matrix")
print(Vh)

s[RANK:] = 0

print("\n\nReduced ∑ diagonal matrix by Rank "+str(RANK))
print(s)
new_a = np.dot(np.diag(s), Vh)
new_a = new_a[~np.all(new_a == 0, axis=1)]

print("\n\nReduced TF-IDF matrix of Rank "+str(RANK))
print(new_a)
plt.title("Documents plot in 2D vector space")
plt.scatter(new_a[0], new_a[1])
plt.show()