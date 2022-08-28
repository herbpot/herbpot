from sklearn.neighbors import KNeighborsRegressor as knn

from sklearn.model_selection import train_test_split as ttt

import pandas as pd

project = 'empty'

file = pd.read_csv(f'./{project}/flavors_of_cacao.csv')
# print(file)

file = file.drop(['Company','Specific Bean Origin','REF','Review Date','Company Location','Bean Type','Broad Bean Origin'],axis=1)

file.columns = ['0','1']

cocoa = file["0"]
rating = file['1']
# print(cocoa,rating)
# print('-------------------------')
for i in range(101) :
    for k in range(101):
        cocoa = cocoa.replace(f'{i}.{k}%',float(f'{i}.{k}'))
    cocoa = cocoa.replace(f'{i}%',i)
        
cocoa, rating = cocoa.to_numpy(),rating.to_numpy()
# print(cocoa,rating)
# print(type)

train_input, test_input, train_target, test_target = ttt(cocoa,rating,random_state=42)
print(train_input, train_target, test_input, test_target)

train_input = train_input.reshape(-1,1)
test_input = test_input.reshape(-1,1)


kn = knn()
kn.fit(train_input,train_target)
score = kn.score(test_input,test_target)
print(score)
score = kn.score(train_input,train_target)
print(score)

