
import serial
import codecs

ser = serial.Serial('/dev/COM3', 9600)

while True:
    text = ser.read()

    if text == "G":
       
        print('<img src="GREEN.png" alt="GREEN" style="width:450px;height:306px;">')
        
        
        
        
    if text == "R":
        print('<img src="RED.png" alt="RED" style="width:450px;height:306px;">')
        


