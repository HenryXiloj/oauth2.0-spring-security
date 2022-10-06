# Detailed description and steps for run project found here: 
https://jarmx.blogspot.com/2022/10/microservices-security-patterns-and.html

# oauth2.0-spring-security

Go to http://localhost:8082/ui : </br>

Internally to make this:  </br>
https://YOUR_DOMAIN/authorize? </br>
    response_type=code& </br>
    client_id=YOUR_CLIENT_ID& </br>
    redirect_uri=https://YOUR_APP/callback& </br>
    scope=SCOPE& </br>
    state=STATE </br>

Ldap user : </br>
Username: henry </br>
Password: 123 </br>

![alt text](https://github.com/HenryXiloj/oauth2.0-spring-security/blob/master/oauth1.PNG?raw=true)


And back to client, where we get the authorization code for generated Access Token for access API. </br>

![alt text](https://github.com/HenryXiloj/oauth2.0-spring-security/blob/master/oauth2.PNG?raw=true)

Get the code from URL and then we can generated the Access Token, this way: </br>

curl --request POST \ </br>
  --url 'https://YOUR_DOMAIN/oauth/token' \ </br>
  --header 'content-type: application/x-www-form-urlencoded' \ </br>
  --data grant_type=authorization_code \ </br>
  --data 'client_id=YOUR_CLIENT_ID' \  </br>
  --data client_secret=YOUR_CLIENT_SECRET \ </br>
  --data code=YOUR_AUTHORIZATION_CODE \ </br>
  --data 'redirect_uri=https://YOUR_APP/callback' </br>
  
POST: http://localhost:8081/auth/oauth/token </br>

If you want to test change the code. </br>

![alt text](https://github.com/HenryXiloj/oauth2.0-spring-security/blob/master/oauth3.PNG?raw=true)

With Access Token we can access for API. </br>

GET: http://localhost:8081/auth/rest/hello?access_token=your_token </br>

![alt text](https://github.com/HenryXiloj/oauth2.0-spring-security/blob/master/oauth4.PNG?raw=true)
 
