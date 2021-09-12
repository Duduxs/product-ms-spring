docker container rm --force product-ms && docker container rm --force postgres

docker image rm $(docker images 'product-ms')
docker image rm $(docker images 'postgres')

echo "containers & suas imagens foram parados e removidos com sucesso!"
