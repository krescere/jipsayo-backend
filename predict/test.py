import pickle

def fun1():
    tmpJson={'data': [{'latitude': '127.1413382', 'longitude': '36.8261598', 'roadAddress': '충남 천안시 서북구 성정공원3길 4', 'danjiName': '학산리젠다빌 3차'}, {'latitude': '127.1413382', 'longitude': '36.8261598', 'roadAddress': ' 천안시 서북구 성정공원3길 4', 'danjiName': '학산리젠다빌 3차'}], 'message': '부동산 전체 조회 성공', 'timestamp': '2023-01-12T20:54:17.169478', 'errors': None}
    #print(tmpJson['data'])
    start=[127.1413382,36.8261598]
    input=[]
    for house in tmpJson['data'] :
        print("house['latitude'] :", house['latitude'])
        print("house['longitude'] :", house['longitude'])
        input.append([start[0],start[1],house['latitude'], house['longitude']])
    return input
def fun2(input):
    with open("./model_20230111.pickle","rb") as file:
        model=pickle.load(file)
    result=model.predict(input)
    return result
        
if __name__ == '__main__':
    input=fun1()
    print("input :", input)
    result=fun2(input)
    print("result :", result)