# Parser Project
This is a CSV file parser project written with Java Program.
If log file provided, firstly do parsing the file (loading to DB) and then search by given parameters.
After log file parsed different searches can be done...

To run jar file on command line, check usage below:

USAGE:

$ java -jar parser-jar-with-dependencies.jar com.turgul.kemal.main.Parser [--accesslog=<pathToLogFile>] --startDate=<dateTime> --duration=(hourly | daily) --threshold=<number>

To print usage run below command:
$ java -jar parser-jar-with-dependencies.jar com.turgul.kemal.main.Parser --help

Command List:
--accesslog    file        The input to process data (Optional)
--startDate    date        'yyyy-MM-dd.HH:mm:ss' format
--duration     string      It can be 'hourly' or 'daily'
--threshold    number      an integer number bigger than 0
--help         Display this usage guide


Examples:
	$ java -jar parser-jar-with-dependencies.jar --accesslog=D:\\MyFiles\\Java_MySQL_Test\\access.log --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200
	$ java -jar parser-jar-with-dependencies.jar --startDate=2017-02-01.16:05:00 --duration=daily --threshold=350
	
	
	
LOG File Format
---------------
Date, IP, Request, Status, User Agent (pipe delimited, open the example file in text editor)

Date Format: "yyyy-MM-dd HH:mm:ss.SSS"


