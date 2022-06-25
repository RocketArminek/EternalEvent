test:
	docker-compose run gradle gradle test --no-daemon

publish-local:
	docker-compose run gradle gradle publishToMavenLocal --no-daemon

publish:
	docker-compose run gradle gradle publish --no-daemon
