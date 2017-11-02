import urllib
from bs4 import BeautifulSoup

def file_creator(url,Edges,Groups,level):
	try:
		if level == 1:
			return
		html = urllib.urlopen(url)
		soup = BeautifulSoup(html, "lxml")
		links=soup.findAll('a',href=True)
		no_links=len(links)								#Number of links on current page

		for i in range(no_links):

			link=links[i]
			href = link.get('href')
			if not href.endswith(('pdf','PDF')):
				edge='{"source":"'+url+'","target":"'+href+'","levels":"'+str(level)+'"},\n'
				group='{"name":"'+href+'","levels":"'+str(level)+'"},\n'
				
				Groups.write(group)
				Edges.write(edge)
				file_creator(href,Edges,Groups,level+1)
		
	except:
		print("Error:404 - Page not found ")


main_url='http://www.du.ac.in/du/index.php?page=study'
Edges=open('Edges.json','w+')
Edges.write('{"links":[')
Groups=open('Groups.json','w+')
Groups.write('{"nodes":[')

file_creator(main_url,Edges,Groups,0)

Edges.seek(-2,2)
Edges.write("]}")
Groups.seek(-2,2)
Groups.write("]}")