'''
Created on Sep 14, 2017

@author: Lakshya
'''
from progressbar import ProgressBar, Percentage, Bar, ETA
from time import sleep


progress, progress_maxval = 0, 10
pbar = ProgressBar(widgets=['Progress ', Percentage(), Bar(), ' ', ETA(), ],
                   maxval=progress_maxval).start()

for i in range(progress_maxval):
    progress += 1
    sleep(1)
    pbar.update(progress)

pbar.finish()