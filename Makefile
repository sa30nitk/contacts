
all: clean deps test

test:
	lein test

clean:
	lein clean

deps:
	lein deps

coverage:
	lein cloverage

lint:
	lein eastwood
