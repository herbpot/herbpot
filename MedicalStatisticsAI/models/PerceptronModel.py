import tensorflow as tf
from tensorflow.python import keras
from tensorflow.python.keras import layers
import pandas as pd
from tensorflow.python.keras.losses import MeanAbsoluteError

data = pd.read_csv('./models/data/insurance.csv')

del data['region']


data['sex'] = data['sex'].replace('male',0).replace('female',1)
data['smoker'] = data['smoker'].replace('yes',1).replace('no',0)
data = data.astype({'charges':'int','sex':'int','smoker':'int'})

data_x = data.copy()
data_y = data_x.pop('charges')

fetures = data.columns

# print(fetures)
# print(data.info())
# print(data.head())

# print(data_x.head())
# print(data_y.head())


class Main():
    def __init__(self) :
        self.setup_model()
    
    def setup_model(self):
        self.model = keras.models.Sequential()
        self.model.add(layers.Dense(5,input_dim=5))
        self.model.add(layers.Dense(1))
        self.model.compile(optimizer='adam',loss=MeanAbsoluteError,metrics=['accuracy'])
        self.model.fit(data_x,data_y,epochs=5)
        pass 

    async def predict(data):
        result = 0
        return result



#test#
mainmodel = Main()
#test#