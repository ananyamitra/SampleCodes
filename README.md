1. OAuthHandler can be placed in the same package from where we call gettoken Currently
2. The package string in OAuthHandler needs to be changed to have the package name it was copied to
3. Put the clientId and tokenUrl correctly in the OAuthhandler file
4. In the code where we call getToken now, we can comment the getToken call and instead do this:
	
	OAuthHandler oauthHandler = new OAuthHandler();
	String token = oauthHandler.getToken();
