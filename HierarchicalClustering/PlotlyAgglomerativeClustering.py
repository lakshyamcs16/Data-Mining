'''
Created on Nov 2, 2017

@author: Lakshya
'''
# needed imports

from tfidf.tfidfusingsklearn import cosine_similarity_matrix_2D
import plotly.plotly as py
import plotly.figure_factory as ff
import plotly
import numpy as np

'''
http://scikit-learn.org/stable/modules/generated/sklearn.cluster.AgglomerativeClustering.html

class sklearn.cluster.AgglomerativeClustering(n_clusters=2, affinity=’euclidean’, 
memory=None, connectivity=None, compute_full_tree=’auto’, linkage=’ward’, pooling_func=<function mean>)[source]
output of clustering for 2 clusters: [111001100]
means documents 0, 1, 2, 5, 6 are in one cluster
and documents 3, 4, 7, 8 are in second cluster
'''
from sklearn.cluster import AgglomerativeClustering
Z = AgglomerativeClustering().fit_predict(cosine_similarity_matrix_2D)

print(Z)

plotly.tools.set_credentials_file(username='username', api_key='********************')
dendro = ff.create_dendrogram(np.array(cosine_similarity_matrix_2D))
dendro['layout'].update({'width':1000, 'height':800})
py.iplot(dendro, filename='simple_dendrogram')
