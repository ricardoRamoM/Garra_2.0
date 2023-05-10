// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  Joystick controlPlacer = new Joystick(1);

  WPI_VictorSPX motorBrazo = new WPI_VictorSPX(11);
  WPI_VictorSPX motorMano = new WPI_VictorSPX(12);

  private double compensador=0;
  private double bajar=0;

  
  boolean cubo,cono;

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //movimiento brazo
    if (controlPlacer.getRawButton(5)==true )
    {//boton 5
      compensador=.25;
    }
    else {//boton 8
      if (controlPlacer.getRawButton (8)==true) {
        compensador=0.08;
      }
      //valor del compensador con la garra vacia
      else {
        compensador=-0.04;
      }
    }
    if(controlPlacer.getRawButton(2)==true){ //boton 2
      bajar=0.25;
    }
    if(controlPlacer.getRawButton(4)==true){ //boton 4
      bajar=0;
    }
    
    motorBrazo.set(controlPlacer.getRawAxis(5)+compensador+bajar);
    
    if(controlPlacer.getRawAxis(5)<-0.05) //eje 5
    {
      bajar=0;
    }


    //garra
       
   
      if(controlPlacer.getRawAxis(3)>0.1) //eje 3
      {
        motorMano.set(-controlPlacer.getRawAxis(3));
        cono=true;
      }
      else
      { 
        if(cono==true)
          {
            motorMano.set(-0.32);
          }
          else
          {
            motorMano.set(0);
          }
      }
    
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    motorBrazo.set(controlPlacer.getRawAxis(1));

  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
