
# coding: utf-8

# ## Loading data into postgres
# 
# 1. Create table and set encodings manually (can be extracted from the given target_car.txt file by copying upto ALTER command)
# 2. Remove everything which is above COPY command in target_car.txt file
# 3. run it in command prompt (make sure to set environment variable for psql) 
#    psql -h localhost -d postgres -U postgres < "Path_to_the_file\target_car.txt"
# 4. Run the following script one by one.

# ## Normalizing data
# 
# Removed extra columns which can be extrapolated from the given information and exported to excel

# In[34]:

# After clearing everything till COPY:
# psql -h localhost -d postgres -U postgres < "D:\AndroidUdacity\Data Analysis\target_car.txt"

import json
import pandas as pd
import psycopg2 as pg

FILE_PATH = 'D:\\AndroidUdacity\\Data Analysis\\'
with open(FILE_PATH+'supplier_car.json') as f:
    complete_json_data = []
    for line in f:
        j_content = json.loads(line)
        del j_content['TypeNameFull']
        del j_content['TypeName']
        complete_json_data.append(j_content)
        
df.to_excel(FILE_PATH+'normalized_data.xls', index=False)
print('Step 1: Normalized Supplier Data')


# ## Extracting Data
# 
# Converted the given attributes to columns and kept the relevant ones which are in accordance with target_car dataset

# In[189]:

mileage = []
mileage_unit = []
df = pd.read_excel(FILE_PATH+'normalized_data.xls', sheet_name=0) # can also index sheet by name or fetch all sheets
df = df.pivot_table(values='Attribute Values', index=['ID', 'MakeText', 'ModelText','ModelTypeText'], columns='Attribute Names'
, aggfunc='first')
df = df[[ 'BodyColorText', 'BodyTypeText', 
         'City', 'ConditionTypeText', 'ConsumptionTotalText', 'DriveTypeText',
         'FirstRegMonth', 'FirstRegYear' ]]

for col in df['ConsumptionTotalText'].values:
        if (str(col) == 'nan'):
            mileage.append(' ')
            mileage_unit.append(' ')
        else:
            mileage.append(col[0:-2])
            mileage_unit.append(col[-2:])
del df['ConsumptionTotalText']
df['mileage'], df['mileage_unit'] = mileage, mileage_unit
df.to_excel(FILE_PATH+'Extracted_data.xls')
print('Step 2: Extracted relevant data from the normalized supplier data')


# ## Integrating Supplier data
# 
# 1. Renaming columns to match with the current schema so that both the datasets can be integrated.
# 2. Executing PostgreSQL commands to insert the data into the database table

# In[206]:

df = pd.read_excel(FILE_PATH+'Extracted_data.xls', sheet_name=0) # can also index sheet by name or fetch all sheets
  
df.rename(columns = {
                   'MakeText': 'make', 
                   'ModelText': 'model',
                   'ModelTypeText': 'model_variant',
                   'BodyColorText': 'color',
                   'BodyTypeText': 'carType', 
                   'City': 'city', 
                   'ConditionTypeText': 'condition', 
                   'DriveTypeText': 'drive',
                   'FirstRegMonth': 'manufacture_month', 
                   'FirstRegYear': 'manufacture_year'}, 
                                 inplace = True) 
df = df.drop(df.columns[[0]], axis=1)
df.to_csv(FILE_PATH+'Extracted_data.csv', encoding='utf-8-sig', index=False)


# In[216]:

conn = psycopg2.connect("host='localhost' port='5432' dbname='postgres' user='postgres' password='welcome,'")
conn.set_client_encoding('UTF8')
cur = conn.cursor()
f = open(FILE_PATH + 'Extracted_data.csv', 'r')
sql = '''target_car(make, model, model_variant, color, "carType", city, condition, drive, manufacture_month, manufacture_year, mileage, mileage_unit)'''
cur.copy_from(f, sql, sep=',')
conn.commit()
f.close()


# ## Generating Excel sheet for target data
# 
# Running PostgreSQL commands to fetch and save the data in an Excel sheet

# In[230]:

#!/usr/bin/python
import os
import psycopg2

output_file = FILE_PATH + 'Integrated Data.xls'
try:
    conn = psycopg2.connect("host='localhost' port='5432' dbname='postgres' user='postgres' password='welcome,'")
except:
    print("Connection failed")

cur = conn.cursor()

try:
    cur.execute('''select * from target_car''')
except:
    print('Enable to execute query')
rows=cur.fetchall()
try:
    import xlwt
except ImportError: "import of xlwt module failed"

# Make spreadsheet
workbook = xlwt.Workbook()
worksheet = workbook.add_sheet(os.path.split(output_file)[1])

worksheet.set_panes_frozen(True)
worksheet.set_horz_split_pos(0)
worksheet.set_remove_splits(True)

# Write rows
for colidx,heading in enumerate(cur.description):
    worksheet.write(0,colidx,heading[0]) # first element of each tuple

# Write rows
for rowidx, row in enumerate(rows):
    if rowidx != 0:
        for colindex, col in enumerate(row):
            worksheet.write(rowidx+1, colindex, col) # increment `rowidx` by 1

# All done
workbook.save(output_file)
#print"finished"


print('Step 3: Integrated Excel Sheet Generated!')
conn.commit()
conn.close()


# In[ ]:



