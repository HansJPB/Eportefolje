#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,
                                 InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile



# This program requires LEGO EV3 MicroPython v2.0 or higher.
# Click "Open user guide" on the EV3 extension tab for more information.


# Create your objects here.
ev3 = EV3Brick()


# Write your program here.
ev3.screen.print("Hello World")
wait(2000)
left_motor = Motor(Port.A) #setter venstre motor til port A
right_motor = Motor(Port.D) #setter høyre motor til port D
robot = DriveBase(left_motor, right_motor, 25, 68) #Definerer right og left motor som drivebase (samler begge motorene, enklere enn å bruke de hver for seg)

for i in range(2):  #Lager en løkke som skal repeteres to ganger. lager en kort og langside to ganger.
    robot.straight(100)
    wait(1000)
    robot.turn(90)
    wait(1000)
    robot.straight(200)
    robot.turn(90)
wait(2000)
ev3.screen.print("Have a nice day")
wait(5000)