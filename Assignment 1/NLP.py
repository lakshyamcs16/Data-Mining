'''
Created on Aug 24, 2017

@author: Lakshya
'''
import nltk

sentence = """Mr. Lakshya, you're a genius! Would you tell me how are things in DUCS? I am sure they are not as bad as they are in my head. Haha"""
tokens = nltk.word_tokenize(sentence)   
print(tokens)

print(nltk.sent_tokenize(sentence))

tagged = nltk.pos_tag(tokens)
print(tagged[0:6])

entites = nltk.chunk.ne_chunk(tagged)
print(entites)

#from nltk.corpus import treebank

#t = treebank.parsed_sents("wsj_0001.mrg")[0]
#t.draw()