docker run --network custom-network-1 --name ls-doc -p 5433:5432 -e POSTGRES_USER=doc -e POSTGRES_PASSWORD=doc -e POSTGRES_DB=learning-system -d -v C:\Users\orlov\docker_volume:/var/lib/postgresql/data postgres:16.2
docker build -t image-two-url .
docker run --network custom-network-1 --env-file=pr_doc.env --name cont-7 -p 8080:8080 -d image-two-url