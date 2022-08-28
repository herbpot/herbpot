import numpy as np
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression

class data :
    def __init__(self):
        self.list = []

    def setlist(self,leng):
        for i in range(leng):
            try:
                self.list.append([self.list[self.list.__len__()-1][0] + (i+1)**4])
            except IndexError:
                self.list.append([1])
    
    def getlist(self):
        return self.list
    
    def getlist_(self):
        return [i[0] for i in self.list]
    

rawdata = data()
rawdata.setlist(9000)
y = rawdata.getlist()
x = [[i+1] for i in range(9000)]
x_train = x[:8000]
x_test = x[8000:]

y_train = y[:8000]
y_test = y[8000:]

poly = PolynomialFeatures(degree=5, include_bias=False)

x_train_ = poly.fit_transform(x_train)
x_test_ = poly.fit_transform(x_test)

print('모델 훈련중')
model = LinearRegression()
model.fit(x_train_,y_train)
print("정확도 : ",model.score(x_test_,y_test))
    
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import RMSprop,Adam,SGD

model_ = Sequential()
model_.add(Dense(1, input_shape=(None,5)))

# print(np.array([i[0] for i in y_train]))
# opti = Adam(lr = 0.001)
model_.compile(loss='mse', optimizer='sgd')
model_.fit(x_train_,np.array([i[0] for i in y_train]),epochs=100,batch_size=70)

ans = ''
while True:
    ans = input('>>>')
    if ans == "exit" or ans == "ㄷ턋" :
        print('프로그램 종료')
        break
    if ans == 'change':
        ans = input('change model >>>')
        if ans == "model_":
            use_model = model_
        elif ans == 'model':
            use_model = model
    poly = PolynomialFeatures(degree=5, include_bias=False)
    x_pre = poly.fit_transform([[int(ans)]])
    print(x_pre)
    print(use_model.predict(x_pre))