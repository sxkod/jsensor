# jsensor
A java robot, that reads a http get link and processes accelerometer/gyroscope data, then uses a mapping to automate keyboard and mouse.

I created this while creating a esp8266 connected MPU6050 sensor that runs as a wifi connected device. Think Steam Controller. Basically this package runs on the desktop, reads the device as a http get source, parses the data, reads a mapping file, pretends to launch keys and mouse actions based on the mapping.

