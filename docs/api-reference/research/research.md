# Research 저장

## 명세

{% swagger method="post" path="/api/v1/research" baseUrl="{{domain}}" summary="설문 조사 저장을 위한 API 입니다." expanded="true" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="body" name="savedMoney" type="Long" required="true" %}
보유한 현금
{% endswagger-parameter %}

{% swagger-parameter in="body" name="moneyPerMonth" type="Long" required="true" %}
매달 저축가능 금액
{% endswagger-parameter %}

{% swagger-parameter in="body" name="jibunAddress" type="String" required="false" %}
지번 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="roadAddress" type="String" required="false" %}
도로명 주소
{% endswagger-parameter %}

{% swagger-parameter in="body" name="increaseRate" type="Double" required="false" %}
인상률
{% endswagger-parameter %}

{% swagger-parameter in="body" name="job" type="String" %}
직업
{% endswagger-parameter %}

{% swagger-parameter in="body" name="occupation" type="String" required="false" %}
직종
{% endswagger-parameter %}

{% swagger-parameter in="body" %}

{% endswagger-parameter %}

{% swagger-response status="201: Created" description="설문조사 삽입 성공" %}
{% code lineNumbers="true" %}
```json
{
    "id":$research_id
}
```
{% endcode %}
{% endswagger-response %}
{% endswagger %}

### Request 예시

{% tabs %}
{% tab title="curl" %}
```powershell
curl --location --request POST 'localhost:8080/api/v1/research' \
--header 'Content-Type: application/json' \
--data-raw '{
    "savedMoney": 100,
    "moneyPerMonth": 10,
    "jibunAddress": "한남동 810",
    "increaseRate": 0.5
}'
```
{% endtab %}

{% tab title="http" %}
```
POST /api/v1/research HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 111

{
    "savedMoney": 100,
    "moneyPerMonth": 10,
    "jibunAddress": "한남동 810",
    "increaseRate": 0.5
}
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "savedMoney": 100,
  "moneyPerMonth": 10,
  "jibunAddress": "한남동 810",
  "increaseRate": 0.5
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("localhost:8080/api/v1/research", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
var axios = require('axios');
var data = JSON.stringify({
  "savedMoney": 100,
  "moneyPerMonth": 10,
  "jibunAddress": "한남동 810",
  "increaseRate": 0.5
});

var config = {
  method: 'post',
  url: 'localhost:8080/api/v1/research',
  headers: { 
    'Content-Type': 'application/json'
  },
  data : data
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

{% tabs %}
{% tab title="JSON" %}
```json
{
    "id": 1
}
```
{% endtab %}
{% endtabs %}