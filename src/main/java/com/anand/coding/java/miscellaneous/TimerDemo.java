package com.anand.coding.java.miscellaneous;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * TimerDemo class
 */
class TimerDemo implements ActionListener
{
    /**
     *
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        Date now = new Date();
        System.out.println("At this tone, the time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {

        final String inputString = JOptionPane.showInputDialog("Timer Delay (seconds)?");
        final int timerDelay = Integer.parseInt(inputString)*1000;

        ActionListener listener = new TimerDemo();
        Timer timer = new Timer(timerDelay, listener);

        timer.start();
        JOptionPane.showMessageDialog(null,"Quit Program?");
        System.exit(0);
    }
}
