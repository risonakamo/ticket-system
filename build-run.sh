set -ex
HERE=$(dirname $(realpath $BASH_SOURCE))

mvn clean package
java -jar ./target/incident-ticket-system-0.0.1-SNAPSHOT.jar