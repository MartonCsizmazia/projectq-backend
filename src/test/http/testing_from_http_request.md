# Testing by HTTP request:

## From Postman:

1. Authentication:

* POST request to localhost:8080/auth/signin
* Body:
    ```json
    {
        "username": "user",
        "password": "password"
    }
    ```

2. Using the usual features authorized:

* POST request to localhost:8080/
* Authorization -> 
    * TYPE: choose "Bearer Token"
    * Token: paste the token received in the auth request response 
    (just in itself, eg. eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU3NjAwNDE2NCwiZXhwIjoxNTc2MDA3NzY0fQ.qhjRuu7dmxSRS2sn3PGipELxqTEx6G8ccONHTsCR8uM)


---

## From cURL:
(copy this into command line)
(convert Postman -> cURL: click "Code" (right top), select cURL

1. Authentication:
```
curl -X POST   http://localhost:8080/auth/signin    -H 'Content-Type: application/json'    -d '{
"username": "user",
"password": "password"
}'

```
2. Using the usual features authorized, eg.:
```
curl -X POST \
  http://localhost:8080/ \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU3ODE0NDY0NywiZXhwIjoxNTc4MTQ4MjQ3fQ.Ok7U17K1NteGMyF1Fu7hdMAH3RVz2DOGLXe20832P9k' \
  -H 'Content-Type: application/json' \
```

---
## From IntelliJ:

(rightclick on sidebar -> New -> HTTP Request)

1. Authentication:
```
POST http://localhost:8080/auth/signin
Content-Type: application/json

{
"username": "user",
"password": "password"
}

```
2. Using the usual features authorized, eg.:
```
POST http://localhost:8080/
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTU3ODE0NDY0NywiZXhwIjoxNTc4MTQ4MjQ3fQ.Ok7U17K1NteGMyF1Fu7hdMAH3RVz2DOGLXe20832P9k
Content-Type: application/json
```
(or: Convert from cURL )

-----------