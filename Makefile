JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
SERVER = server
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server*.jar


$(TARGET):
	$(MAVEN) -f $(SOUNDBOARD) package


$(DUMMY):
	$(MAVEN) -f $(SERVER) package


.PHONY: run
run: $(TARGET)
	$(JAVA) -jar $(TARGET)


.PHONY: start_dummy
start_dummy: $(DUMMY)
	$(JAVA) -jar $(DUMMY)


.PHONY: shutdown_dummy
shutdown_dummy:
	@if test -f "server.PID";then\
		echo "Checking status of dummy server..";\
		if ps -p `cat server.PID`;then\
			echo "Shutting down running dummy server process..";\
			kill `cat server.PID`;\
		fi;\
		echo "Removing PID cache file..";\
		rm -f server.PID;\
	fi;


# Rebuilds & runs the Soundboard against a dummy server.
.PHONY: debug
debug:
	$(MAVEN) -f $(SOUNDBOARD) clean
	$(MAKE) start_dummy & echo $$! > server.PID&
	$(MAKE) run
	$(MAKE) shutdown_dummy


.PHONY: clean
clean:
	$(MAKE) shutdown_dummy
	$(MAVEN) -f $(SOUNDBOARD) clean
	$(MAVEN) -f $(SERVER) clean
