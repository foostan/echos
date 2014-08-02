echos
====================
echos is simple web application that return messages sent by someone, powered by playframework with scala.

## Web UI
```
GET /echos
```

## Send message and return echos
```
POST /api/1.0/echos
```

### Input
| Name    | Description                          |
|:--------|:-------------------------------------|
| message | Required. A message to be send.      |
| number  | Number of next echos. Default is 10. |

### Example
```
$ curl -s -X POST -d "message=echo1" localhost:9000/api/1.0/echos | jq .
{
  "echos": [],
  "echo": "echo1"
}
$ curl -s -X POST -d "message=echo2" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo1"
  ],
  "echo": "echo2"
}
$ curl -s -X POST -d "message=echo3" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo2",
    "echo1"
  ],
  "echo": "echo3"
}
$ curl -s -X POST -d "message=echo4" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo3",
    "echo2",
    "echo1"
  ],
  "echo": "echo4"
}
$ curl -s -X POST -d "message=echo5" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo4",
    "echo3",
    "echo2",
    "echo1"
  ],
  "echo": "echo5"
}
$ curl -s -X POST -d "message=echo6&number=2" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo5",
    "echo4",
    "echo3",
    "echo2",
    "echo1"
  ],
  "echo": "echo6"
}
$ curl -s -X POST -d "message=echo7&number=1" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo6",
    "echo5"
  ],
  "echo": "echo7"
}
$ curl -s -X POST -d "message=echo8" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo7"
  ],
  "echo": "echo8"
}
$ curl -s -X POST -d "message=echo9" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo8",
    "echo7"
  ],
  "echo": "echo9"
}
$ curl -s -X POST -d "message=echo10" localhost:9000/api/1.0/echos | jq .
{
  "echos": [
    "echo9",
    "echo8",
    "echo7"
  ],
  "echo": "echo10"
}
```

## Run on Docker
```
$ activator clean compile stage
$ docuker build -t echos .
$ docker run -p 9000 -d echos
```
