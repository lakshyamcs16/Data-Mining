'''
Created on Sep 20, 2017

@author: Lakshya
'''
from sklearn.feature_extraction.text import TfidfVectorizer
import glob, os
from nltk.corpus import stopwords
from nltk.tokenize import RegexpTokenizer
from scipy import spatial
import nltk

tokenizer = RegexpTokenizer(r'\w+')
path = 'D://Eclipse Workspace//NLP//Assignment//customcorpus//'
    
corpus_list = []
for filename in glob.glob(os.path.join(path, '*.txt')):
    fout = open(filename, 'rb')
    corpus_list.append(fout.read().decode('utf-8').lower())

processed_corpus = []
for sentence in corpus_list:
    stop_words = set(stopwords.words("english"))
    words  =  tokenizer.tokenize(sentence)  
    processed_sentence = [i for i in words if not i in stop_words and not i.isnumeric() and len(i) > 1]
    processed_corpus.append(processed_sentence)


fdist_list = []
for st in processed_corpus:
    fdist = nltk.FreqDist(st)
    fdist_list.append(fdist)
    print(st)
    
'''
idf = log N / df(t)

'''    
tfidf_vectorizer = TfidfVectorizer(min_df=1, lowercase=False,  tokenizer=lambda doc:doc, analyzer='word', norm='l2')
tfidf_matrix = tfidf_vectorizer.fit_transform(processed_corpus)

matrix_array = tfidf_matrix.toarray()

cosine_similarity_matrix = []
cosine_similarity_matrix_2D = []

documents = []

print("\n\nCosine Similarity Matrix")
for documents in enumerate(matrix_array):
    for doc in enumerate(matrix_array, start = documents[0]+1):
        result = 1 - spatial.distance.cosine(documents[1], doc[1])
        cosine_similarity_matrix.append(result)
    cosine_similarity_matrix_2D.append(cosine_similarity_matrix)  
    cosine_similarity_matrix = []  

for i in cosine_similarity_matrix_2D:
    for val in i:
        print(str(round(val,5))+"\t\t", end="")
    print("\n")
        
print("\n\nTF-IDF Matrix")
print (tfidf_matrix.toarray())

