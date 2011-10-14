package client;

import javax.swing.JPanel;

import org.jdesktop.application.SingleFrameApplication;

/**
 * 
 */
public class NewSingleFrameApplication extends SingleFrameApplication {
    private JPanel topPanel;

    @Override
    protected void startup() {
        topPanel = new JPanel();
        topPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        show(topPanel);
    }

    
}
