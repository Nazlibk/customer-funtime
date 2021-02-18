#Customer Fun Time

This solution is over-engineered to make it close to production.
The solution uses the parallel stream of Java so, the file can be processed faster depends on the CPU cores number.
If input file is a very big file, we can use Spark.

## How to build project

This is a Maven project so, simply run ```mvn clean package```
Note : you need to have Internet connection to be able to download dependencies. 
During build time, all the test will be run and a fat Jar i.e```customers-funtime.jar``` will be generated in the ```target``` folder.

## How to run

To run the application ```java -jar customers-funtime.jar -i /SOME_PATH/MY_INPUT_FILENAME``` (use the input file name that you have).
A copy of Jar file is already available on the parent folder. 

## Result

You can find output result for the given input in the parent folder (```output.txt```) in the CSV format.
