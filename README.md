# edu-tech-smarterap
Tool to better prepare high school students for the Advanced Placement tests.

### Notes:
* Frontend: edu-tech-aprep-static
* Backend: edu-tech-aprep-war

### How to Run Backend
* Download: Intellij, Java 7, Maven 3 (latest), Jetty
* From Intellij:
	* New -> Project From Existing Source
	* Import project from external model -> Maven 
		* Here are my options:
		* Import Maven projects automatically
		* Create Intellij IDEA modules for aggregator
		* Keep source and test folders on reimport
		* Exclude build directory
		* Use Maven output directories
		* Everything else default
	* Select profiles: jetty
	* Select Maven projects to import: edu.gatech:smart-ap:0.0.1-SNAPSHOT
	* Add Java 7 JDK
	* And Finish!
* To Run:
	* Run menu -> Edit Configs
	* Click the '+' in the top left -> Maven
	* Name: smarterap-war jetty:run (or whatever you want)
	* Working Directory: path to war folder
	* Command line: jetty:run
	* Profiles: jetty
	* Everything else was default for me
	* Run -> jetty:run