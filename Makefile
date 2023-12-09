SHELL := /bin/bash
# Executables
JAVA = java
MAVEN = mvn
# Artifact IDs
SOUNDBOARD = soundboard
SERVER = server
# Compile Targets
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar
# Dev Folders
RELEASES = RELEASES/


$(TARGET):
	$(MAVEN) package --projects $(SOUNDBOARD)


$(DUMMY):
	$(MAVEN) package --projects $(SERVER)


$(RELEASES):
	@if ! test -d $(RELEASES);then mkdir $(RELEASES) & echo "Created release directory @ $(RELEASES)."; fi;


.PHONY: run
run: ARGS?=
run: $(TARGET)
	$(JAVA) -jar $(TARGET) $(ARGS)


.PHONY: run_dummy
run_dummy: ARGS?=
run_dummy: $(DUMMY)
	@$(MAKE) stop_dummy
	@echo "Starting dummy server..."
	@$(JAVA) -jar $(DUMMY) $(ARGS) & echo $$! > server.PID&


.PHONY: stop_dummy
stop_dummy:
	@if test -f "server.PID";then\
		echo "Checking status of dummy server..";\
		if ps -p `cat server.PID`;then\
			echo "Shutting down running dummy server process...";\
			kill `cat server.PID`;\
		fi;\
		echo "Removing PID cache file..";\
		rm -f server.PID;\
	fi;


# Runs a clean snapshot of the Soundboard against a dummy server instance.
.PHONY: snapshot
snapshot:
	@echo "Rebuilding $(SOUNDBOARD)..."
	@$(MAVEN) clean compile exec:java --projects $(SOUNDBOARD)
	@$(MAKE) run_dummy
	@$(MAKE) stop_dummy


.PHONY: clean_release
clean_release: $(RELEASES)
	@if test -d $(RELEASES);then\
		if compgen -G "$(RELEASES)*.jar" > /dev/null; then\
			echo "Removing jars found in $(RELEASES).";\
			rm $(RELEASES)*.jar;\
		fi;\
	fi;


.PHONY: clean
clean:
	@$(MAKE) stop_dummy
	@echo Cleaning object code..
	@$(MAVEN) clean
	@$(MAKE) clean_release


.PHONY: version
version: v?=
version:
	@if test -z "$$v"; then\
		echo "Rule requires 'v=' arg to specify target version.";\
		exit 1;\
	fi;
	@echo "Updating $(SOUNDBOARD)'s version..."
	@$(MAVEN) versions:set -DnewVersion=$(v) --projects $(SOUNDBOARD)/pom.xml > /dev/null


.PHONY: release
release: v?=
release: $(RELEASES)
	@if ! test -z "$$v"; then\
		echo "Versioning was specified.";\
		$(MAKE) version v=$(v);\
	fi;
	@echo "Repackaging $(SOUNDBOARD) jar..."
	@$(MAVEN) clean package --projects $(SOUNDBOARD)  > /dev/null
	@echo "Checking for jars in $(RELEASES).."
	@$(MAKE) clean_release
	@echo "Copying latest $(SOUNDBOARD) jar to $(RELEASES).."
	@cp $(TARGET) $(RELEASES)
