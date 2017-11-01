'''
Created on Aug 25, 2017

@author: Lakshya
'''
import nltk 
sentence = [("the", "DT"), ("little", "JJ"), ("yellow", "JJ"), ("dog", "NN"), ("barked","VBD"), ("at", "IN"), ("the", "DT"), ("cat", "NN")]

pattern = """NP: {<DT>?<JJ>*<NN>}
VBD: {<VBD>}
IN: {<IN>}"""
NPChunker = nltk.RegexpParser(pattern) 
result = NPChunker.parse(sentence)
result.draw()