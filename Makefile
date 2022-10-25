##
# awesome-hello
#
# @file
# @version 0.1

sources := $(shell git ls-tree --name-only -r HEAD)

uberjar: ./target/uberjar/awesome-hello-standalone.jar

./target/uberjar/awesome-hello-standalone.jar: $(sources)
	lein uberjar

.PHONY: docker
docker: uberjar
	docker build -t $(USER)/awesome-hello .

# end
