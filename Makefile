default: clean
	./build.sh

clean:
	./build.sh clean

docs:
	./build.sh docs

fmt:
	./build.sh fmt

submit:
	./build.sh submit

sync:
	./build.sh sync

run: clean
	./build.sh run

