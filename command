podman build -t java-webapp .
podman stop java-app
podman rm java-app
podman run -d -p 8080:8080 --name java-app --network=host java-webapp
curl http://localhost:8080/api/hello

