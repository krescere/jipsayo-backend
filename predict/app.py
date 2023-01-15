from flask import Flask, request
from flask_sqlalchemy import SQLAlchemy
import pickle
import config
import json

# init app
app=Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI']=config.SQLALCHEMY_DATABASE_URI
# init database
db=SQLAlchemy()
db.init_app(app)

# all houses
houses=[]

# open pre-trained model
with open("./model_20230111.pickle","rb") as file:
        model=pickle.load(file)

# controller
# 필터링 기능
@app.route('/api/v1/houses/filter')
def house_filter():
    # houses 가 비어있으면 초기화
    if houses.__len__()==0:
        house_reload()
    
    # get filter info
    start={'latitude':request.args.get('latitude'),
           'longitude':request.args.get('longitude')}
    time=request.args.get('time')
    time=int(time)
    
    # predict input
    input=make_predict_input(start)
    # predict
    response=[]
    predict_times=model.predict(input)
    for i in range(0,len(predict_times)):
        predict_time=int(predict_times[i])
        if predict_time<=time:
            house=houses[i]
            response.append(HouseResponse(house['id'],predict_time).__dict__)
    
    # return as JSON
    return json.dumps(response)

# 부동산 갱신 TODO : scheduler 로 주기적으로 실행하게
@app.route('/api/v1/houses/reload')
def house_reload():
    resultproxy=db.session.execute(db.select(
        House.id,
        House.latitude,
        House.longitude
        ))
    
    # initialize
    houses.clear()
    for row in resultproxy:
        row_as_dict=row._asdict()
        houses.append(row_as_dict)
    return "houses reloaded : "+str(len(houses))+" houses"

# service
def make_predict_input(start):
    input=[]
    for house in houses :
        input.append([start['longitude'],start['latitude'], house['longitude'],house['latitude']])
    return input

def predict(input):
    results=model.predict(input)
    return results

# DTO
class HouseResponse(object):
    id=""
    time=""
    def __init__(self, id, time):
        self.id = id
        self.time = time
        
# Entity
class House(db.Model):
    id=db.Column(db.Integer, primary_key=True)
    latitude=db.Column(db.Float, nullable=False)
    longitude=db.Column(db.Float, nullable=False)

# main
if __name__ == '__main__':
    app.run()