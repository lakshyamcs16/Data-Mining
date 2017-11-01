'''
Created on Aug 25, 2017

@author: Lakshya
'''
import nltk
from nltk.corpus import state_union
from nltk.tokenize import PunktSentenceTokenizer

train_text  = state_union.raw("2005-GWBush.txt")
#sample_text = state_union.raw("2006-GWBush.txt")

sample_text = "I live in Delhi"
custom_sent_tokenizer = PunktSentenceTokenizer(sample_text)

nltk.help.upenn_tagset()

tokenized = custom_sent_tokenizer.tokenize(sample_text)

def process_content():
    try:
        for i in tokenized:
            words = nltk.word_tokenize(i)
            tagged = nltk.pos_tag(words)
            print(tagged)
            
    except Exception as e:
        print(str(e))
        
process_content()