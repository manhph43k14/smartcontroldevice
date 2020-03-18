#include "ESP8266WiFi.h"
#include <ESP8266HTTPClient.h>
#include <FirebaseArduino.h>
#define FIREBASE_HOST "iot-001-84230.firebaseio.com" //Thay bằng địa chỉ firebase của bạn
#define FIREBASE_AUTH ""   //Không dùng xác thực nên không đổi

int ir_sensor = 5; // D1
int relayInput = 2; // D4
int relayDoor = 14; // D5
int cBusy1 = 0;
int cFree1 = 0;
int isBusy1 = 0;  
int sensor1IsBusy = LOW;
int light;
int door;

const char *ssid = "Bufflo";
const char *password = "123456789";
WiFiClient client;

void setup()
{
  Serial.begin(115200);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.print('.');
  }

  Serial.println();
  Serial.println(F("Wifi network connected! "));
  Serial.print(F("IP of ESP8266 (Socket Client ESP8266): "));
  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

  // sensor
  pinMode(sensor1IsBusy, INPUT);
  //  relay
  pinMode(relayInput, OUTPUT);
  // door
  pinMode(relayDoor,OUTPUT);
}

void loop() {
  sensor1IsBusy = digitalRead(ir_sensor);
//  Serial.println("Sensor kicked");

    if (sensor1IsBusy == LOW) {
    cFree1 = 0;
    cBusy1++;
    if (cBusy1 == 3)
    {
      Serial.println("Sensor: busy ");
      Serial.println("------------------");
      cBusy1 = 0;
      isBusy1 = 1;
    }
  } else {
    delay(300);
    cFree1++;
    cBusy1 = 0;
    if (cFree1 == 6)
    {
      Serial.println("Sensor: free ");
      Serial.println("------------------");
      cFree1 = 0;
      isBusy1 = 0;
    }
  }
  // call relay controller
  relayController (isBusy1);
}

void relayController (int isBusy1){
  if (isBusy1 == 1){
    light  = Firebase.getInt("light");
    if(light == 1){
      digitalWrite(relayInput, LOW); 
    } else {
      digitalWrite(relayInput, HIGH);
      
    }
    door  = Firebase.getInt("door");
    if(door == 1){
        digitalWrite(relayDoor,LOW);
    } else {
        digitalWrite(relayDoor,HIGH);
    }
    Serial.print("Relay is on ");
    Firebase.set("isSensorBusy", isBusy1);
     
    if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());
      return;
    }
  }
  else if (isBusy1 == 0){
    digitalWrite(relayInput, HIGH);
    light = 0;
    door  = Firebase.getInt("door");
    if(door == 0){
      digitalWrite(relayDoor,HIGH);
    } else {
      digitalWrite(relayDoor,LOW);
    }
    Serial.print("Relay is off ");
    Firebase.set("isSensorBusy", isBusy1);
    Firebase.set("light",light);
    if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());
      return;
    }
  }
}
