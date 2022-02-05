.DEFAULT_GOAL := build
ENVIRONMENT := local

.PHONY: help
help: ## Show make targets
	@bash ./makefile_scripts/help.sh

.PHONY: infrastructure
infrastructure: ## Run infrastructure services
	@docker-compose up -d postgres sonarqube elasticsearch logstash kibana minio kafka

.PHONY: build
build: ## Package the app
	@make build -f Makefile.$(ENVIRONMENT)

.PHONY: deps
deps: ## Download and install dependencies
	@make deps -f Makefile.$(ENVIRONMENT)

.PHONY: install
install: ## Move the binary into the deployment dir
	@make install -f Makefile.$(ENVIRONMENT)

.PHONY: start
start: ## Start the app
	@make start -f Makefile.$(ENVIRONMENT)

.PHONY: deploy
deploy: deps build install start ## Deploy the app

.PHONY: unit-test
unit-test: ## Run unit tests
	@make unit-test -f Makefile.$(ENVIRONMENT)

.PHONY: test
test: ## Run tests
	@make test -f Makefile.$(ENVIRONMENT)