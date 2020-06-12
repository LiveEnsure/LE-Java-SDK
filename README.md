# LiveEnsure Java SDK

This is the LiveEnsure® JAVA SDK for LiveEnsure Authentication (www.liveensure.com)
>From this SDK you will be able to launch a full API stack in JAVA, XAMARIN FORMS and demonstrate the 
full capabilities of LiveEnsure® Authentication for web, cloud, apps and mobile.

The SDK is provided to illustrate how to integrate LiveEnsure® with your identity, web or
app platforms. For futher information, visit the SDK docs at http://developer.liveensure.com 

You will need to obtain LiveEnsure® developer API keys to test this API and sign up for a 
paid LiveEnsure service subscription to use the API/SDK in production. 

This SDK functions in desktop-browser and mobile-browser (app to app) modes, depending on how
you access the pages. From a desktop, you will scan with the free LiveEnsure app to authenticate
based on the settings you configure to drive the API. If you acccess the pages from your mobile
device, the SDK will behave in an app-to-app fashion, rolling over to authenticate as in an app
or mobile HTML implementation, without providing or requiring a scan step. 

For questions about this SDK or LiveEnsure® authentication, visit support.liveensure.com 

## Getting Started

* **First,** sign up at http://www.liveensure.com/signup.html for your developer Agent Id, API key and password with your valid email address.
* **Second,** download the free LiveEnsure app for iOS or Android http://www.liveensure.com/app.html
* **Third,** download, configure and run this SDK on your local or networked machine as instructed.

### Prerequisites

Below packages need to be installed and configured:
- network accessible server (or virtual instance)
- JDK 8.0+ applications.
- Obtain LiveEnsure® developer API keys by signup (http://www.liveensure.com/signup.html) and then click the link "Send me my credentials by email" after login
- Google MAP api key (optional for location factors)

### Installing

To install `LEJavaLibrary` app, follw the given steps
    
	add LEJavaLibrary.jdk in the project 

### Configuration

* Call leStartSession from Config.java:
```
	Config.leStartSession( le email, le Agent Id, le Api Key, le Api Pass )
```

* Make any challenge from Challenge.java

```
```

## Running the SDK

Explain how to launch, run the automated tests for this system
Make sure you cover how to bind Java library to extenral IP so they
can see it from their phone if need be (ifconig -a, find IP, etc)

### Desktop/Browser Authentication via Mobile Scan

Walk through each factor tab and how to test/engage desktop with mobile scan.

[1] To test to basic device authentication factor, simply click [DEVICE] tab,
enter your email and click login. Scan screen to authenticate. 

On your first login, you will get an emailed OOB token to enter/register in app.

[2] To test knowledge challenge, click the [Knowledge] tab, enter your email and
choose a challenge question and anwner/response. This is teeing up the API, as the
end-user would not see this. Then click and scan, enter response to challenge
in the device, to authenticate. 

[3] To test location, click the [Location] challenge, enter email and click
the map to register your current lat/long. If you want location to pass,
simply login, scan and pass location proximity test. If you want it to fail,
drag the map to an alternate location, login and fail due to proximity difference.

[4] To test behavior, click the [Behavior] tab and choose an orientation, and a 
grid pattern (up to 2 places) for single or multi-touch on the device. 

Click login and touch and hold the desired locations AS YOU SCAN, until it beeps. 
To fail, tap wrong locations on the screen or release before you scan. 

In the demo you can only choose one factor per test, but in the full API SDK
you can add multiple challenges in combination as needed.

### App to App Mobile Authentication via App Only

Walk through each factor and how to test/engage from mobile browser to
the mobile app, from app-to-app (no QR scan).

First, access the demos from a mobile browser on iOS on Android.

For the mobile demos, repeat the steps as above, except you will rollover
from mobile browser to app to authenticate after each "login" press/tap.

For behavior, you must hold the touch locations while and until the clock sweeps,
since there is no scan.

The rest of the demos function as they do in the desktop version.

## Using the SDK with your own stack/app/code

To use the SDK in your own code, You can copy `LEJavaLibrary.jar` in your own stack. It is a class based
implementation of all the api, which internally calls the liveensure API using `requests`.

This can be used as follows:

- Create LiveEnsure object (required)

```  
	# api_key is the API key for liveensure
	# api_password is the API password for liveensure 
	# agent_id is the Agent ID for liveensure
	# host_to_access_API is the Host location where API's are hosted
  
	# Make sure you have all these keys before you start using the APIs
```

- Start Session (required)

```      
	# email is the user id for which authentication is to be done

	Config.leStartSession( le email, le Agent Id, le Api Key, le Api Pass )
```

	It will return JSON object which have the `sessionToken`, that will be used in all subsequent calls.
  

- Add factors (optional)

	* Add biometric challenge for v6
          
		```
			# Make a request to create a v6 biometric challenge
			
			# touches is your fingureprint on mobile phone sensor
		```

	* Add behaviour challenge for v5
          
		```
			# Make a request to create a v5 behavior challenge
			
			# orientation is the orientation of mobile phone used to scan the 
			# code. It can have 4 values range from 0 to 3
			# 0 -> Portrait
			# 1 -> Upside down
			# 2 -> Landscape Left
			# 3 -> Landscape Right
			
			# touches number of touch points up to 6
		```
		  
	* Add behaviour challenge for v6
          
		```
			# Make a request to create a v6 behavior challenge
			
			# touches is number of touch points up to 9 and design a pattern between 9 dots
		```
		  
	* Add location challenge for v5

		```
			# Make a request to create a v5 location challenge
			
			# lat is latitude of location
			# lang is the longitude of the location
			# radius is the radius limit of location authentication
		```
		  
	* Add location challenge for v6

		```
			# Make a request to create a v6 location challenge
			
			# lat is latitude of location
			# lang is the longitude of the location
			# radius is the radius limit of location authentication
			# inout is inside the radius or outside the radius
		```
		  
    * Add knowledge challenge

		```
			# Make a request to create a knowledge challenge
			
			# question is the question to be asked after scanning the code
			# answer is the answer to the question 
			# session Token is the session key that is returned by initSession call.
		```
		  
	* Add time challenge for v6

		```
			# Make a request to create a v6 time challenge
			
			# startDate is the combination of date and time
			# endDate is the combination of date and time 
			# inout is inside the time lap or outside the time lap
		```
		  
	* Add wearable challenge for v6

		```
			# Make a request to create a v6 wearable challenge
			
			# deviceId is list of all wearable devices
		```

	It will return json object with status of the API call.

    

    
- Get the session token (required)

```
	Config.leStartSession( le email, le Agent Id, le Api Key, le Api Pass )
```

- Create the login object/link to scan or tap (required)

```
	TBD ....
```
This is the moment the user scans or taps on mobile to authenticate
and performs their authentication factors as configured above. 
During this process you may poll for status in the background.

- Poll for session status (required)

```      
	Challenges.pollStatus( le Session Token, le Agent Id )
```

- Delete user (optional)

```    
	# email: email of the user that is to be deleted
  
	Config.deleteUser( le email, String Agent Id )
```

## Built With

* JDK 8.0+
* Any IDE(eclipse)
* Google Map APIs
* LiveEnsure Authentication APIs


## Versioning

Current Version: **1.0**

## Authors

* **Christian Hessler** - *Author* - [LiveEnsure](https://github.com/LiveEnsure)
* **Abhinay Gupta** - *Author* - [LiveEnsure](https://github.com/LiveEnsure)


## License

This project is proprietary software (c) 2020 LiveEnsure Inc. 
Visit http://www.liveensure.com for more information.

## Contact

* Web: http://www.liveensure.com 
* Dev: http://developer.liveensure.com
* Support: http://support.liveensure.com 

=======
