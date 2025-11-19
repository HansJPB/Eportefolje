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
obstacle_sensor = UltrasonicSensor(Port.S4) #definerer ultralydsensor på port C
left_motor = Motor(Port.D) #venstre motor for port D
right_motor = Motor(Port.A) #høyre motor for port A #touch-sensor for port B
color_sensor = ColorSensor(Port.S1)
robot = DriveBase(left_motor, right_motor, wheel_diameter=25, axle_track=68)
robot.settings(turn_rate = 20)
#Variabler som kreves for linje-kjøring
BLACK = 7 #målt refleksjonsverid for svart.
WHITE = 37 #målt refleksjonsverdi for hvit
terskel = (BLACK + WHITE) / 2 #kreves for at den skal følge linjen. Dette er midtpunktet.
fart = 100
pro_gain = 1.2 #kreves for å kunne rette opp.

#Stoppeklokke til underholdninga
klokke = StopWatch()
random_tall = 0


def dans1(): #funksjon for dans 1
        robot.turn(90)
        robot.turn(-90)

def joke(): #funksjon for joken
    ev3.speaker.say("Why do programmers not like nature?")
    wait(30)
    ev3.speaker.say("It has too many bugs!")

def dans2(): #funksjon for dans 2
    robot.turn(360)

def clanker(): #funksjon for clanker
    ev3.speaker.say ("Roger Roger")
    ev3.speaker.say("I am a clanker")
    ev3.speaker.say("I am a stupid clanker")

while True:
    reflection = color_sensor.reflection() #verdien som måles
    avvik = reflection - terskel #hvor stort avvik det er mellom den målte veriden og terskelen den skal være innenfor
    turn_rate = pro_gain * avvik #bestemmer hvor mye robotten skal svinge for å holde seg på linja
    #ev3.screen.print(color_sensor.reflection()) måle lysrefleksjonen.

    if klokke.time() >= 10000: #skjer hvert 10.sekund. 
        robot.stop()
        random_tall = random.randint(1,4) #velger tilfeldig nummer.
    
        if random_tall == 1: #if-statements hvor funksjonene har sitt eget tall.
            dans1()
        elif random_tall == 2:
            joke()
        elif random_tall == 3:
            dans2()
        elif random_tall == 4:
            clanker()
        random_tall = 0
        klokke.reset()
    robot.drive(fart, turn_rate) #bruker turn_rate som regnes ut over
    if obstacle_sensor.distance() < 150: #når den oppdager et hinder
            robot.stop()
            ev3.speaker.play_file(SoundFile.CHEERING)
            wait(100000)