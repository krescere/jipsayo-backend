from flask import Flask
import pandas as pd
import pickle
import requests

app = Flask(__name__)

# controller
@app.route('/', methods=['GET'])
def hello_world():
    return 'OPEN PORT : 5000'

@app.route('/houses/filter', methods=['GET'])
def filter_houses():
    # get all houses from the database
    url="http://localhost:8080/api/v1/houses/all"
    response = requests.get(url)
    print("response ",response)
    print("response.json() ",response.json())

    # predict
    return "filter houses success"

# service
def predict(houses):
    # predict expect minute
    with open("./model_20230111.pickle","rb") as file:
        model=pickle.load(file)
    return houses

# main
if __name__ == '__main__':
    app.run()