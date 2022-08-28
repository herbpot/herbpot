from flask import Flask, redirect, render_template, request, session, url_for
from models.PerceptronModel import Main as pMain
from models.DecisionTreeClassifier import Main as dMain
import asyncio
import json

with open('../models/data/data.json','r') as F :
    data = json.load(F)

print(data)

#setup AI models
perceptron = pMain()
decision = dMain()
###

app = Flask('Medic AI')
app.secret_key = data.secretKey

@app.route('/')
def main():
    return render_template('main.html')

@app.route('/process')
def process():
    reqform = request.form
    if reqform in data.datalist :
        session['userdata'] = reqform
        session['isPro'] = True
    else:
        return redirect('/')

@app.route('/result')
def result() :
    return render_template('result.html')