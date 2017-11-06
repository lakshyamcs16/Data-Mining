import igraph as igr
import json
import plotly
import plotly.plotly as py
from plotly.graph_objs import Scatter3d, Line, Layout, XAxis,YAxis, Scene, ZAxis, Marker, Annotations, Annotation, Data, Margin, Font, Figure

plotly.tools.set_credentials_file(username='apooos3', api_key='z2yZqlKj1z4JA6WR938R')

data=[]
with open("Edges.json") as datafile:
	data=json.load(datafile)

group_data=[]
with open("Groups.json") as groupfile:
	group_data=json.load(groupfile)

L= len(data['links'])

#***********************************************************************************************
link_value={}
Edges=[]
counter=0
for x in range(L):
	source=data['links'][x]['source']			#create hashmap for mapping links to integers
	target=data['links'][x]['target']
	if  source not in link_value:
		link_value[source]=counter
		counter=counter+1
	if  target not in link_value:
		link_value[target]=counter
		counter=counter+1
		Edges.append((link_value[source],link_value[target]))



labels=[]
group=[]
for node in group_data['nodes']:
	labels.append(node['name'])
	group.append(node['levels'])

#************************************************************************************************

Edge_len=len(Edges)
G=igr.Graph(Edges,directed=False)
layt=G.layout('kk', dim=3)

Xn=[layt[k][0] for k in range(Edge_len)]# x-coordinates of nodes
Yn=[layt[k][1] for k in range(Edge_len)]# y-coordinates
Zn=[layt[k][2] for k in range(Edge_len)]# z-coordinates
Xe=[]
Ye=[]
Ze=[]
for e in Edges:
	Xe+=[layt[e[0]][0],layt[e[1]][0], None]# x-coordinates of edge ends
	Ye+=[layt[e[0]][1],layt[e[1]][1], None]
	Ze+=[layt[e[0]][2],layt[e[1]][2], None]

trace1=Scatter3d(x=Xe,
               y=Ye,
               z=Ze,
               mode='lines',
               line=Line(color='rgb(125,125,125)', width=1),
               hoverinfo='none'
               )

trace2=Scatter3d(x=Xn,
               y=Yn,
               z=Zn,
               mode='markers',
               name='actors',
               marker=Marker(symbol='dot',
                             size=6,
                             color=group,		#group
                             colorscale='Viridis',
                             line=Line(color='rgb(50,50,50)', width=0.5)
                             ),
               text=labels,		#label
               hoverinfo='text'
               )

axis=dict(showbackground=False,
          showline=False,
          zeroline=False,
          showgrid=False,
          showticklabels=False,
          title=''
          )

layout = Layout(
         title="Link Structure for Delhi University - Academics",
         width=1000,
         height=1000,
         showlegend=False,
         scene=Scene(
         xaxis=XAxis(axis),
         yaxis=YAxis(axis),
         zaxis=ZAxis(axis),
        ),
     margin=Margin(
        t=100
    ),
    hovermode='closest',
    annotations=Annotations([
           Annotation(
           showarrow=False,
            text="",
            xref='paper',
            yref='paper',
            x=0,
            y=0.1,
            xanchor='left',
            yanchor='bottom',
            font=Font(
            size=14
            )
            )
        ]),    )

data=Data([trace1, trace2])
fig=Figure(data=data, layout=layout)

py.plot(fig, filename='Crawler')
