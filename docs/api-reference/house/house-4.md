# House 필터링

## 명세

{% swagger method="get" path="/houses/filter" baseUrl="{{domain}}" summary="부동산 필터링을 위한 API 입니다." expanded="true" %}
{% swagger-description %}
조건에 따른 부동산을 검색할때 사용합니다. 내부적으로 예측 서버를 사용합니다.
{% endswagger-description %}

{% swagger-parameter in="query" name="latitude" type="Double" required="true" %}
위도
{% endswagger-parameter %}

{% swagger-parameter in="query" name="longitude" type="Double" required="true" %}
경도
{% endswagger-parameter %}

{% swagger-parameter in="query" name="cost" type="Long" required="true" %}
가격
{% endswagger-parameter %}

{% swagger-parameter in="query" name="time" type="Long" required="true" %}
시간
{% endswagger-parameter %}

{% swagger-parameter in="query" name="count" type="Int" %}
반환받을 갯수(디폴트:500)
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="부동산 필터링 성공" %}
```json
{
    "id": $house_id,
    "jibunAddress": "$house_jibunAddress",
    "roadAddress": "$house_roadAddress",
    "cost": $house_cost,
    "latitude": $house_latitude,
    "longitude": $house_longitude,
    "createdDate": "$house_createdDate",
    "modifiedDate": "$house_modifiedDate"
}
```
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location --request GET 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses/filter?latitude=36.8261598&longitude=127.1613382&cost=8000&time=60'
```
{% endtab %}

{% tab title="HTTP" %}
```
GET /api/v1/houses/filter?latitude=36.8261598&longitude=127.1613382&cost=8000&time=60 HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var axios = require('axios');

var config = {
  method: 'get',
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses/filter?latitude=36.8261598&longitude=127.1613382&cost=8000&time=60',
  headers: { }
};

axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});

```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');

var config = {
  method: 'get',
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/houses/filter?latitude=36.8261598&longitude=127.1613382&cost=8000&time=60',
  headers: { }
};

axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});

```
{% endtab %}
{% endtabs %}

### Response 예시

{% hint style="info" %}
프론트로는 10개가 동일하게 반환되는데 우선순위 큐를 사용하여 \[이동시간 오래걸리는 순, 비싼 순] 으로 정렬하여 상위 10개가 반환됩니다.

ex) GET 강남역 1시간 3억 요청시&#x20;

강남부근 59분 3억 \
강남부근 59분 2억 \
강남부근 55분 3억\
...
{% endhint %}

{% tabs %}
{% tab title="JSON" %}
```json
{
    "data": [
        {
            "id": 432,
            "jibunAddress": "강원 동해시 천곡동 1080",
            "danjiName": "동해천곡주공1차아파트",
            "cost": 7000,
            "latitude": 37.5246989,
            "longitude": 129.107194,
            "time": 59,
            "dealDate": "2022-11-29T00:00:00",
            "dedicatedArea": 51.09
        },
        {
            "id": 3711,
            "jibunAddress": "강원 동해시 천곡동 415",
            "danjiName": "주공4차아파트",
            "cost": 7500,
            "latitude": 37.520164,
            "longitude": 129.1036572,
            "time": 57,
            "dealDate": "2022-11-18T00:00:00",
            "dedicatedArea": 39.99
        },
        {
            "id": 9596,
            "jibunAddress": "충남 아산시 둔포면 둔포리 242-6",
            "danjiName": "성우아파트",
            "cost": 5500,
            "latitude": 36.9309449,
            "longitude": 127.0410273,
            "time": 57,
            "dealDate": "2022-10-18T00:00:00",
            "dedicatedArea": 40.5
        },
        {
            "id": 22525,
            "jibunAddress": "강원 동해시 천곡동 1004-5",
            "danjiName": "시영2차아파트",
            "cost": 6500,
            "latitude": 37.5163383,
            "longitude": 129.1078867,
            "time": 55,
            "dealDate": "2022-04-29T00:00:00",
            "dedicatedArea": 49.89
        },
        {
            "id": 36272,
            "jibunAddress": "강원 동해시 천곡동 1051-3",
            "danjiName": "효성타운5차",
            "cost": 7300,
            "latitude": 37.5205317,
            "longitude": 129.1055003,
            "time": 57,
            "dealDate": "2019-11-16T00:00:00",
            "dedicatedArea": 84.98
        },
        {
            "id": 39995,
            "jibunAddress": "강원 횡성군 둔내면 둔방내리 84-1",
            "danjiName": "남서울아파트",
            "cost": 6500,
            "latitude": 37.5131829,
            "longitude": 128.2143573,
            "time": 55,
            "dealDate": "2022-09-27T00:00:00",
            "dedicatedArea": 84.84
        },
        {
            "id": 1994,
            "jibunAddress": "충남 아산시 둔포면 둔포리 285-1",
            "danjiName": "비둘기아파트",
            "cost": 5000,
            "latitude": 36.9267123,
            "longitude": 127.0443268,
            "time": 55,
            "dealDate": "2022-11-24T00:00:00",
            "dedicatedArea": 46.57
        },
        {
            "id": 11975,
            "jibunAddress": "강원 동해시 천곡동 965-1",
            "danjiName": "범주성지1차아파트",
            "cost": 5300,
            "latitude": 37.5171728,
            "longitude": 129.1179526,
            "time": 55,
            "dealDate": "2022-09-30T00:00:00",
            "dedicatedArea": 59.95
        },
        {
            "id": 11951,
            "jibunAddress": "강원 횡성군 둔내면 둔방내리 590-4",
            "danjiName": "경림아파트",
            "cost": 8000,
            "latitude": 37.5115912,
            "longitude": 128.210252,
            "time": 54,
            "dealDate": "2022-09-30T00:00:00",
            "dedicatedArea": 58.189
        },
        {
            "id": 31144,
            "jibunAddress": "강원 동해시 천곡동 1012",
            "danjiName": "삼성타워",
            "cost": 8000,
            "latitude": 37.5181314,
            "longitude": 129.1153292,
            "time": 55,
            "dealDate": "2021-06-16T00:00:00",
            "dedicatedArea": 66.6
        }
    ],
    "message": "부동산 필터링 조회 성공",
    "timestamp": "2023-01-19T11:19:33.204081",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
