The authentication protocol that the PCF service uses is OAUTH2.

It is a 2 step process:
step 1 - The client appilcation needs to identify itself to the authorization server using a Client Id and a Client secret. 
         The authorization server gives back a token to the client application.  
Step 2 - The client application uses the token in any subsequent request to the web service to get authenticated data.


OAUTH2 uses 'Scopes' to limit an application's access. An application can request one or more scopes, and the access token issued to the application will be limited to the scopes granted.  
We noticed in the example codes that we need to use the 'Openid Profile' scope to get the correct token. Basically this was the reason why initially we were getting the wrong scope (Cause we were not passing any scope value and I believe that the authorization server was using some kind of a default scope/)

Also, in our specific case, we have TLS enabled and hence the client does not need to identify itself using a secret, but it can use a certificate (the public keys of which has already been imported to the server's truststore).  

So for the coding part we needed to do the following:  

1. Make a standard webservice call to the authorization server url setting the appropriate client ID, certificates and the scope.
   The server responds with the token.  
2. Use the token as a request header in any subsequent calls to the webservice.  

(We leveraged standard hava HttpURLConnection classes to make the webservice calls and setting the appropriate certificates through SSLConnectionFactory APIs)
