from flask import Flask
import pandas as pd
import pickle

app = Flask(__name__)

@app.route('/', methods=['GET'])
def hello_world():
    return 'OPEN PORT : 5000'

@app.route('/houses/filter', methods=['GET'])
def filter_houses():
    # get all houses from the database
    # read model
    with open("./model_20230111.pickle","rb") as file:
        model=pickle.load(file)
        
    # predict



if __name__ == '__main__':
    app.run()