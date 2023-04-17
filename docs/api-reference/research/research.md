# Research 저장

## 명세

{% swagger method="post" path="/api/v1/research" baseUrl="{{domain}}" summary="설문 조사 저장을 위한 API 입니다." expanded="true" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="body" name="houseDetailId" type="Long" required="true" %}
부동산 상세 아이디
{% endswagger-parameter %}

{% swagger-parameter in="body" name="savedMoney" type="Long" required="true" %}
보유한 현금
{% endswagger-parameter %}

{% swagger-parameter in="body" name="moneyPerMonth" type="Long" required="true" %}
매달 저축가능 금액
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
curl --location 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/research' \
--header 'Content-Type: application/json' \
--data '{
    "houseDetailId":1,
    "savedMoney":100,
    "moneyPerMonth":10,
    "increaseRate":0.5
}'
```
{% endtab %}

{% tab title="http" %}
```
POST /api/v1/research HTTP/1.1
Host: ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com
Content-Type: application/json
Content-Length: 100

{
    "houseDetailId":1,
    "savedMoney":100,
    "moneyPerMonth":10,
    "increaseRate":0.5
}
```
{% endtab %}

{% tab title="JavaScript(Fetch)" %}
```javascript
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "houseDetailId": 1,
  "savedMoney": 100,
  "moneyPerMonth": 10,
  "increaseRate": 0.5
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/research", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
```
{% endtab %}

{% tab title="NodeJs(Axios)" %}
```javascript
const axios = require('axios');
let data = JSON.stringify({
  "houseDetailId": 1,
  "savedMoney": 100,
  "moneyPerMonth": 10,
  "increaseRate": 0.5
});

let config = {
  method: 'post',
  maxBodyLength: Infinity,
  url: 'ec2-3-37-157-108.ap-northeast-2.compute.amazonaws.com/api/v1/research',
  headers: { 
    'Content-Type': 'application/json'
  },
  data : data
};

axios.request(config)
.then((response) => {
  console.log(JSON.stringify(response.data));
})
.catch((error) => {
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
    "data": {
        "id": 1
    },
    "message": "설문조사 저장 성공",
    "timestamp": "2022-12-27T18:38:32.3525661",
    "errors": null
}
```
{% endtab %}
{% endtabs %}
