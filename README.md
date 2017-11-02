# Data-Mining
Assignment on Introductory Information retrieval 

This assignment mainly contains 5 files:
File 1: datamining/ducs_crawler.py
This file is used to crawl a website (number of hops i.e., how deep you want to crawl within the website) can be defined by the user.
It downloads the webpages in text format and all the PDF files as well.

File 2: datamining/ducs_pdf_to_corpus.py
This file converts all the downloaded PDF files which contains only text, in text format. 
It also creates a corpus which is used later.

File 3: linkstructure/input_file_marler.py and linkstructure/graph.py
These files create a 3D graph of link structure using plotly. Again, the number of hops to be shown in the graph can be given as input.

File 4: tfidf/tfidfusingsklearn.py
It uses the NLTK library to preprocess the corpus generated before. It removes stop words, considers the length of words greater than 1 and removes all the numbers. It uses utf-8 decoding, calculates frequency distribution of terms in the documents. 
It creates a TF-IDF matrix using sklearn.feature_extraction.text.
It calculates cosine score and creates a cosine similarity matrix.

File 5: HierarchicalClustering/PlotlyAgglomerativeClustering.py
It performs hierarchical clustering based on cosine similarity matrix and generates a dendrogram on plotly using default parameters which uses ward based linkage with formation of 2 clusters.
