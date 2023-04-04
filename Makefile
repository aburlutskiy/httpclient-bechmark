args = `arg="$(filter-out $@,$(MAKECMDGOALS))" && echo $${arg:-${1}}`

.PHONY: up
up:
	docker-compose up -d

.PHONY: run
run:
	docker run --network httpclient-bechmark_mockserver http-client-test

.PHONY: build
build:
	docker build -t http-client-test .

%:
	@:

