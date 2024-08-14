<div align="center">
  <img src="app/src/main/res/drawable/bipa_logo.png" width=200>
  <h1> Bipa Coding Challenge </h1>
</div>

## Build tools & versions used
- **Kotlin** : Programming language used to develop the app
- **Retrofit** : APIRest request and handling library | v.2.11.0 | com.squareup.retrofit2:retrofit
- **Gson** : Extension and implementation library for JSON data conversion | v.2.11.0 | com.squareup.retrofit2:converter-gson

## Steps to run the app
Before starting to run the app, make sure that the device has a stable internet connection. When carrying out the first execution, it will request permission to use the internet from the user, ensure that this process is designed so that everything can occur normally.

With the application launched, a welcome message will be displayed to give the user further instructions and detail what the application is about. By pressing the "ok" button this message will be indented.

The application will present a button with the description "Request", this button performs all the initialization of the suspension process coroutine which will start the API request algorithm via retrofit, before the data can be displayed a loading spinner will be displayed to the user. When finished, the user will be able to consult all lists of Nodes available at that time.

## What areas of the app did you focus on?

The Application focuses on its main screen, this was done to guarantee the simplicity of the code and to request data efficiently. The main activity has a worked layout in addition to providing adapted design for light and dark themes.

## What was the reason for your focus? What problems were you trying to solve?

The focus of using the main activity is the ease and dynamism of retrieving data, the main activity obtains the ViewBinding resource available for manipulating the components in addition to having a structure for carrying out the main event coroutines.

An important challenge of this application was the adaptation for data visualization, for this it was necessary to use several refactorings in the layout adapter to guarantee a good user experience. Every detail was minimally thought out in order to visualize the required resources.

## How long did you spend on this project?

Around 4 hours for all the implementation of the necessary resources along with the design.

## Did you make any trade-offs for this project? What would you have done differently with more time?

I certainly tried to make the design more intuitive to compensate for the simplicity of the app, I brought with it color resources and an in-depth look at Material Design 3, one of the features available in Android Studio.

An improvement that I would be interested in making in the future would be a new Activity for detailing Nodes, something like a sound feature where the user clicks on the desired Node and the application transfers them to a screen with more detailed resources based on the Node key.

## What do you think is the weakest part of your project?

I believe that the concept behind the back-end of the application, the requested list display feature, made me consider some restrictions for creating the application, which led me to limit all the code behind it. I would like to be able to implement better-designed resources in the future and perhaps expand the entire logic of the application itself.

## Is there any other information you’d like us to know?

I believe that at the root of the project, I created all the packages based on what I learned about the DAO (Data Access Object) methodology, it is a resource that I have been applying for a long time and has helped me in making corrections and implementations already This lets me know where and how each algorithm runs.

<h1 align="center">
   Developed by Rhyan Araujo Chaves @2024
</h1>

