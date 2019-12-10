Testing from Postman:

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

