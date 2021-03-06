package com.uib.leapmotion.darts;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller.PolicyFlag;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by spixy on 9.6.2017.
 */
public class Main {
    private final static boolean useMouse = true;

    public static void main(String[] args) {
        final Gui gui = new Gui();

        Runnable swingStarter = new Runnable()
        {
            @Override
            public void run(){
                gui.start(useMouse);
            }
        };

        SwingUtilities.invokeLater(swingStarter);

        if (!useMouse) {
            Controller controller = new Controller();
            controller.setPolicyFlags(PolicyFlag.POLICY_BACKGROUND_FRAMES);
            Listener listener = new LoopMotionListener(gui);
            controller.addListener(listener);
            // controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
            // controller.enableGesture(Gesture.Type.TYPE_SWIPE);
            controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
            System.out.println("Press Enter to quit...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller.removeListener(listener);
        }
    }
}
