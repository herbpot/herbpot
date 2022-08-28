import glob
import json
from eunjeon import Mecab
from gensim.models import word2vec
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
plt.rcParams["font.family"] = 'NanumGothicCoding'
    

modeltype = input("model type >>> ")
try:
    try:
        model = word2vec.Word2Vec.load(f'./model/word2vec_{modeltype}.model')
    except Exception as e:
        print(e)
        with open(f'{modeltype}_word.txt','r') as f:
            inner = f.read().replace(" ","").split("|")
        data_array = []
        for i in inner:
            data_array.append(i.split(","))
        #     data_array_ = []
        # for i in data_array:

        print("================")
        for i in data_array:
            if len(i) == 1 | len(i) <= 5:
                print("removing ",i)
                data_array.remove(i)
            else:
                for j in i:
                    if len(j) == 1:
                        print("removing ",j)
                        data_array[data_array.index(i)].remove(j)

                print(i)
        model = word2vec.Word2Vec(sentences=data_array, vector_size=150, window=3, min_count=5, epochs=10, workers=2 ,sg=0)
        model.save(f"./model/{modeltype}_word2vec.model")


except Exception as e:
    print(e)
    list = glob.glob(f"../../croler/news/unity/data/{modeltype}/*.json")
    # print(list)
    # Mc('/some/dic/path')
    mc = Mecab("/mnt/d/mecab-ko-dic-2.1.1-20180720")

    wordList = ""
    senList = ""
    print("file list : ",list)
    for i in list:
        with open(i,'r') as f:
            ij = json.load(f)
            for i in ij["it"].split("."):
                wordList += str(mc.nouns(i)).replace("'","").replace("[","").replace("]","") + "|"
            senList += ij["it"] + "|"
    
    # with open('it_sen.txt',"x") as f:
    #     f.write(senList)
    with open(f'{modeltype}_word.txt',"x") as f:
        f.write(wordList)
    
    exit()

print(model)
word_vectors = model.wv
vocabs = word_vectors.key_to_index.keys()
word_vectors_list = [word_vectors[v] for v in vocabs]
# print(word_vectors_list)
# print(model.wv.key_to_index.keys()[:4])
print(model.wv.vectors.shape)
# print(model.wv.vectors)
def plot_2d_graph(vocabs, xs, ys):
    plt.axes().yaxis.set_major_locator(ticker.MultipleLocator(0.005))
    # plt.axes().xaxis.set_major_locator(ticker.MultipleLocator(0.05))
    plt.figure(figsize=(8 ,6))
    plt.scatter(xs, ys, marker = 'o')
    for i, v in enumerate(vocabs):
        plt.annotate(v, xy=(xs[i], ys[i]))

from sklearn.decomposition import PCA
pca = PCA(n_components=2)
xys = pca.fit_transform(word_vectors_list)
xs = xys[:,0]
ys = xys[:,1]
plot_2d_graph(vocabs, xs, ys)
plt.show()
plt.savefig(f"result_{modeltype}.png")

while True:
    ans = input()
    if ans == "end":
        break
    try:
        print(model.wv.most_similar(ans))
    except Exception as e:
        print(e)