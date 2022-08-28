from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import cross_val_score
import pandas as pd


data = pd.read_csv('./models/data/testdata.csv')
print(data)

_ = 1
x = 1


train = data[:_]
valid = data[_:]

train_x = train[:x]
train_y = train[x]
valid_x = valid[:x]
valid_y = valid[x]


class Main():
    def __init__(self):
        self.setup_model()

    def setup_model(self):
        self.model = DecisionTreeClassifier().fit(train_x,train_y)
        pass

    async def predict(self):
        result = 0
        return result 
