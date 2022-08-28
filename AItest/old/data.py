import pandas as pd

project = 'empty'

file = pd.read_csv(f'./{project}/flavors_of_cacao.csv')
# print(file)

file = file.drop(['Company','Specific Bean Origin','REF','Review Date','Company Location','Bean Type','Broad Bean Origin'],axis=1)

file.columns = ['0','1']

cocoa = file["0"]
rating = file['1']
for i in range(100) :
    cocoa = cocoa.replace(f'{i}%',i)
print(cocoa,rating)
print('-------------------------')
cocoa, rating = cocoa.to_numpy(),rating.to_numpy()
print(cocoa,rating)
print(type(cocoa))




#dropname = file[file['Country'] !=]
# dropname = file[file['Continent'] != 'Asia'].index
# file_ = file.drop(dropname)
# print(file_)
# file_ = file_.dropna()
# print(file_)

# file_1 = file_[file_['Country'] == 'South Korea']
# print(file_1)