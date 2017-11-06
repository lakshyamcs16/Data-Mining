'''
Created on Sep 12, 2017

@author: Lakshya
'''

#rd /s "\\?\D:\Eclipse Workspace\NLP\datamining\customcorpus
# importing required modules
import PyPDF2
from progressbar import ProgressBar, Bar, Percentage, ETA
import glob, os
from nltk.corpus.reader.plaintext import PlaintextCorpusReader

def pdf_to_corpus():
    path = 'D://Eclipse Workspace//NLP//Assignment//res//'
    
    for filename in glob.glob(os.path.join(path, '*.pdf')):
        print(filename)
        pdfFileObj = open(filename, 'rb')
    
        # creating a pdf reader object
        pdfReader = PyPDF2.PdfFileReader(pdfFileObj)
     
        # printing number of pages in pdf file
        print(pdfReader.numPages)
     
        # creating a page object
        pageObj = pdfReader.getPage(0)
     
        # extracting text from page
        text = pageObj.extractText()
        
        strings_list = text.split("\n")
        # Make new dir for the corpus.
        corpusdir = 'customcorpus/'
        if not os.path.isdir(corpusdir):
            os.mkdir(corpusdir)
    
        # Output the files into the directory.
        file_name = filename.split("\\")[-1]
        
        print(file_name)
        pbar = ProgressBar(widgets=['Creating Corpus', Bar('#', '[', ']'), ' ',
                                                Percentage(), ' ',
                                                ETA()],maxval=100)
        for text in pbar(strings_list):
            with open(corpusdir+'[PDF] '+file_name+'.txt','ab') as fout:
                    fout.write(text.encode('utf-8'))
        pbar.finish()
    
        #create_corpus(text) 
        corpus = PlaintextCorpusReader('customcorpus/', '.*')
        
        print(corpus.raw())
        
pdf_to_corpus()