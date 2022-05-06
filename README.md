# finhub-app
This is a small app which fetches upcoming IPOs calendar from [Finnhub platform](https://finnhub.io/docs/api/introduction) to experiment around jetpack compose following MVI architecture. 

## Tech Stack
- Kotlin
- Jetpack Compose
- MVI Architecture pattern for UI using [Uniflow](https://github.com/uniflow-kt/uniflow-kt)
- [Clean architeture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) for layered architecture
- [Koin](https://insert-koin.io/) as dependency injection

## Setup
- Go to https://finnhub.io/dashboard
- Create an account & copy api key
- Add below property in your `local.properties` file 

```
API_TOKEN="*************"
```

This will allow you to get success response & see the screens in the app.

### References
- https://github.com/finnhubio/Finnhub-API
- https://finnhub.io/static/swagger.json

