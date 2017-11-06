'''
Created on Sep 20, 2017

@author: Lakshya
'''
mydoclist = ['Julie loves me more than Linda loves me',
'Jane likes me more than Julie loves me',
'He likes basketball more than baseball']

#mydoclist = ['sun sky bright', 'sun sun bright']

from collections import Counter

for doc in mydoclist:
    tf = Counter()
    for word in doc.split():
        tf[word] +=1
    print (tf.items())