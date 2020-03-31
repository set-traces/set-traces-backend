# set-traces-backend
Script writing tool

During our revy "Satte Spor" in the Winter of 2020, we desided that our huge script in GD was too large and way to hard to work with.
Therefor, we desided to create a saas (Script as a service) to help Abakusrevyen achive more :)

Remember to check out our fabules frontend!


## Howoto:
Want to contribute to our backend? Fork this repo and create a pull request. 
Setup to environment for development? Clone this repo. 

Running both database and backend with docker:
```./gradlew build
  docker build -t backend:dev . --no-cache
  docker-compose up
```

Running only database
```
  docker-compose run --service-ports database
```

Running only backend:
```
  ./gradlew bootRun
```

