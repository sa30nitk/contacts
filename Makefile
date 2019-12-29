DB_NAME="contacts_service"
TEST_DB_NAME="contacts_service_test"
DB_PORT=5432

all: clean deps test

db-setup: db-user-create db-create db-migrate

test:
	lein test

clean:
	lein clean

deps:
	lein deps

db-drop:
	dropdb --if-exists -Upostgres $(DB_NAME)

testdb-drop:
	dropdb --if-exists -Upostgres $(TEST_DB_NAME)

db-user-create:
	createuser -S gojek

db-su-create:
	createuser -s gojek -h localhost -U postgres

db-create:
	createdb -p $(DB_PORT) -Ogojek -Eutf8 $(DB_NAME) -h localhost -U gojek

testdb-create:
	createdb -O gojek $(TEST_DB_NAME)

db-migrate:
	lein run migrate

coverage:
	lein cloverage

lint:
	lein eastwood
