set -ex
HERE=$(dirname $(realpath $BASH_SOURCE))
cd $HERE

redoc-cli build -o ../index.html ticket-system-api-1.2.0.yml