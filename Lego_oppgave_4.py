#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,
                                 InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile
import random


ev3 = EV3Brick()
sensor = TouchSensor(Port.S4) #trykksenosr fort port S3
left_motor = Motor(Port.D) #venstre motor for port D
right_motor = Motor(Port.A) #høyr emotor for port A
color_sensor_right = ColorSensor(Port.S2) #venstre farge sensor for port S1
color_sensor_left = ColorSensor(Port.S3) #høyre farge sensor for port S4
robot = DriveBase(left_motor, right_motor, wheel_diameter=25, axle_track=68)
robot.settings(turn_rate = 20)
#Variabler som kreves for linje-kjøring
BLACK_R = 3 #målt refleksjonsverid for svart.
WHITE_R = 48 #målt refleksjonsverdi for hvit
BLACK_L = 5
WHITE_L = 70
terskel_R = (BLACK_R + WHITE_R) / 2 #kreves for at den skal følge linjen. Dette er midtpunktet.
terskel_L = (BLACK_L + WHITE_L) / 2
fart = 30
pro_gain = 1.2 #kreves for å kunne rette opp
lastSensorInput="LEFT"
left_active = False
right_active = False
avvik = 0.01


while True:
    if sensor.pressed(): #Skal skru opp farten betraktelig når knappen trykkes inn (Sabotasje)
        klokke = StopWatch()
        robot.stop()
        while klokke.time() < 2000: #løkken setter opp farten til 100, 
            left_reflection = color_sensor_left.reflection()
            #right_reflection = color_sensor_right.reflection()
            avvik = (left_reflection - terskel_L) - (right_reflection - terskel_R) #avviket fra sensor målingen og terskelen (midtpunktet)
            turn_rate = pro_gain * avvik*0.5 #svinger slikt at den holder seg på linja
            robot.drive(100, turn_rate)
        robot.stop()
    else:
        left_reflection = color_sensor_left.reflection()
        right_reflection = color_sensor_right.reflection()
        #print(left_reflection)

        if (left_reflection - terskel_L > 0):
            # Sanser ikkje svart venstre
            left_active = True
        if (right_reflection - terskel_R > 0):
            # Sanser ikkje svart høgre
            right_active = True
        if (left_reflection - terskel_L <= 0):
            # Sanser svart venstre
            left_active = False
        if (right_reflection - terskel_R <= 0):
            # Sanser svart høgre
            right_active = False

        if (left_active == True and right_active == False):
            # Om venstre sensor er aktiv og høgre sensor ikkje er aktiv:
            # Noter venstre som sist svart linje side.
            lastSensorInput = "LEFT"
        if (left_active == False and right_active == True):
            # Om venstre sensor er ikkje aktiv og høgre sensor er aktiv:
            # Noter høgre som sist svart linje side.
            lastSensorInput = "RIGHT"
        
        if(left_reflection > terskel_L and right_reflection > terskel_R):
            # Om ingen svart sensor input
            # Om begge sensorene er kvite
            if(lastSensorInput=="LEFT"):
                # Svinger til venstre inntil måler svart
                avvik = -0.17*10
            if(lastSensorInput=="RIGHT"):
                # Svinger til høgre inntil måler svart
                avvik = 0.17*10

        else:
            # Om ein eller begge sensorene sanser svart:
            # Gjør standard sving avvik utrekning.
            
            avvik = (left_reflection - terskel_L) - (right_reflection - terskel_R)


        #avvik = (left_reflection - terskel_L) - (right_reflection - terskel_R)
        #avvik = 5
        turn_rate = pro_gain * avvik * 0.4
        robot.drive(fart, turn_rate)
        
    

   
