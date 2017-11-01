'''
Created on Aug 25, 2017

@author: Lakshya
'''
from nltk.stem import PorterStemmer
from nltk.tokenize import word_tokenize

ps = PorterStemmer()

example_words = ["write","writing","written","wrote", "writes"]

new_text = "He loves writing poems. He wrote one last week.\
He has written more than 20 poems thus far. He writes everyday. \
We should learn from him and write at least one poem in a month."

words = word_tokenize(new_text)

for w in words:
    print(ps.stem(w))