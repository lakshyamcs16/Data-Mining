'''
Created on Sep 11, 2017

@author: Lakshya
'''
import requests, datetime
from bs4 import BeautifulSoup
import urllib


def ducs_spider(max_pages):

    page = 1
    while page <= max_pages:
        url = "http://www.du.ac.in/du/index.php?page=study"
        source_code = requests.get(url)
        plain_text = source_code.text
        soup  = BeautifulSoup(plain_text,'html.parser')
        
        '''with open('index.html','w',encoding='utf-8-sig') as f:
            f.write(soup.prettify())  '''
        
        for link in soup.findAll('a',href=True):
            href = link.get('href')
            get_single_item_data(href)
        page +=1
        
        
def get_single_item_data(item_url):
    print("Visiting URL:" + item_url)
    if not item_url.startswith('http://'):
        item_url = "http://www.du.ac.in/du/" + item_url
        
    source_code = requests.get(item_url)
    plain_text = source_code.text
    soup = BeautifulSoup(plain_text,'html.parser')
    
    create_link_document(item_url)
    
    for link in soup.findAll('a',href=True):
        try:
            href = link.get('href')
            print(href)
            
            if not href.startswith('http://'):
                href = "http://www.du.ac.in/du/" + href
                    
            is_pdf = href[-4:]
            filename = href.split("/")[-1]
            if is_pdf.lower() == ".pdf":
                pdf_url = "D://Eclipse Workspace//NLP//Assignment//res//" + filename
                urllib.request.urlretrieve(href, pdf_url)
            else:
                create_link_document(href)    
        except:
            print("Continuing.. ")


def create_link_document(link):



    source_code = requests.get(link)
    plain_text = source_code.text
    corpusdir = 'customcorpus/'
    soup = BeautifulSoup(plain_text,'lxml')
    for script in soup("script"):
        script.extract()
    
    for css in soup("style"):
        css.extract()
    text  = soup.get_text(" ")
    
    moment = datetime.datetime.now().strftime("%H_%M_%S.%f")
    
    filename = soup.title.string+"@"+str(moment) 
    
    print(filename)
    fout = open(corpusdir+filename+"_corpus.txt", "wb")
    fout.write(text.encode('utf-8'))
    fout.close()

ducs_spider(1)
