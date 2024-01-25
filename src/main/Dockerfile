FROM bellsoft/liberica-openjdk-alpine:8u372-7
COPY ./java ./java
RUN mkdir ./out
RUN javac -sourcepath ./java -d out ./java/gb/lesson1/sample/Main.java
CMD java -cp ./out gb.lesson1.sample.Main