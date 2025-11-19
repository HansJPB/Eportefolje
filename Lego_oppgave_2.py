#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,
                                 InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile


ev3 = EV3Brick()
obstacle_sensor = UltrasonicSensor(Port.S4) #definerer ultralydsensor på port C
left_motor = Motor(Port.D) #venstre motor for port D
right_motor = Motor(Port.A) #høyre motor for port A
ts = TouchSensor(Port.S1) #touch-sensor for port B
robot = DriveBase(left_motor, right_motor, wheel_diameter=25, axle_track=68)
#definerer hvilke motorer som skal brukes til å kjøre.
running = False #setter at programmet ikke skal kjøres før den er sann



while True: #evig løkke.
    if ts.pressed():
        while ts.pressed():
            wait(10)
        running = not running #når knappen trykkes, vil den endre running tilstanden til enten true eller false (motsatt)
        ev3.screen.clear()
        if running: #når running = true 
            ev3.screen.print("Exercise 2")
        else: #når running = false.
            ev3.screen.print("Exercise Done")
            robot.stop()

    if running: #hvis running = true
        robot.drive(50, 0)
        if obstacle_sensor.distance() < 300: #når den oppdager et hinder
            robot.straight(-50)
            robot.turn(120)

    wait(10)
    