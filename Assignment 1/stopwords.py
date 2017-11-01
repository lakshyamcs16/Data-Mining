'''
Created on Aug 25, 2017

@author: Lakshya
'''
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords

example_sentence = "Python is a very intuitive programming language. It is necessary to learn Python if you want to work with NLP."
stop_words = set(stopwords.words("english"))
words  =  word_tokenize(example_sentence)

print(word_tokenize(example_sentence))

processed_sentence = [i for i in words if not i in stop_words]

print(processed_sentence)




