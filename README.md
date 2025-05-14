### Docker build & run
```
docker build -t customer-app .
```

```
docker run -d --name customer -p 9000:9000 -e SPRING_PROFILES_ACTIVE=dev customer-app
```
