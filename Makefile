SHELL := /bin/bash
JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
SERVER = server
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar
RELEASE = RELEASES/


$(TARGET):
	$(MAVEN) -f $(SOUNDBOARD) package


$(DUMMY):
	$(MAVEN) -f $(SERVER) package


.PHONY: run
run: ARGS?=
run: $(TARGET)
	$(JAVA) -jar $(TARGET) $(ARGS)


.PHONY: dummy
dummy: ARGS?=
dummy: $(DUMMY)
	$(MAKE) shutdown_dummy
	$(JAVA) -jar $(DUMMY) $(ARGS) & echo $$! > server.PID&


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
	$(MAKE) dummy
	$(MAKE) run
	$(MAKE) shutdown_dummy


.PHONY: deploy
deploy: v?=
deploy:
	@if test -z "$$v"; then\
		echo Missing 'v=' to specifiy version.;\
		exit 1;\
	fi;
	@echo "Updating pom versioning.."
	@$(MAVEN) versions:set -DnewVersion=$(v) -f $(SOUNDBOARD)/pom.xml > /dev/null
	@echo "Rebuilding jar..."
	@$(MAVEN) -f $(SOUNDBOARD) clean package > /dev/null
	@if ! test -d $(RELEASE);then mkdir $(RELEASE) & echo "Created $(RELEASE) directory."; fi;
	@$(MAKE) clean_release
	@echo "Copying latest jar to $(RELEASE)."
	@cp $(TARGET) $(RELEASE)


.PHONY: clean
clean:
	$(MAKE) shutdown_dummy
	$(MAVEN) -f $(SOUNDBOARD) clean
	$(MAVEN) -f $(SERVER) clean
	$(MAKE) clean_release


clean_release:
	@if test -d $(RELEASE);then\
		echo "$(RELEASE) dir exists.";\
		if compgen -G "$(RELEASE)*.jar" > /dev/null; then\
			echo "Removing outdated jars in $(RELEASE).";\
			rm $(RELEASE)*.jar;\
		fi;\
	fi;