DB_NAME="contacts_service"
TEST_DB_NAME="contacts_service_test"
DB_PORT=5432

all: clean deps test

db-setup: db-user-create db-create db-migrate

compile:
	lein compile

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
	createuser -S sa30

db-su-create:
	createuser -s sa30 -h localhost -U postgres

db-create:
	createdb -p $(DB_PORT) -Osa30 -Eutf8 $(DB_NAME) -h localhost -U sa30

testdb-create:
	createdb -O sa30 $(TEST_DB_NAME)

db-migrate:
	lein run migrate

coverage:
	lein cloverage

lint:
	lein eastwood
