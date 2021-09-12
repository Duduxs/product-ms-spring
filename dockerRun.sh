cd "$(find ~ -type d -name product-ms-spring | head -1)"

mvn clean package -Dmaven.test.skip=true

docker image build -t product-ms . -f ./src/main/docker/Dockerfile

docker-compose -f src/main/docker/docker-compose.yaml up -d postgres
docker-compose -f src/main/docker/docker-compose.yaml up -d product-ms

echo "containers gerados com sucesso!"
