# Code courtesy of https://www.techbeamers.com/merge-multiple-csv-files/
import os

csv_out = 'consolidated.csv'
csv_dir = os.getcwd()
print(csv_dir)

csv_merge = open(csv_out, 'w')
dir_tree = os.walk(csv_dir)
for dirpath, dirnames, filenames in dir_tree:
    csv_list = []
    for file in filenames:
       if file.endswith('.csv') and not file == 'consolidated.csv':
          print(file)
          csv_list.append(file)

    

    for file in csv_list:
       csv_in = open(file)
       for line in csv_in:
          csv_merge.write(line)
       csv_in.close()
csv_merge.close()