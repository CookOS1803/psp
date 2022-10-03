package com.cookos;

import java.awt.event.ActionEvent;

import javax.swing.*;

public class MoveAction extends AbstractAction {

    private JComponent component;
    private int moveAmount;

    public MoveAction(String label, JComponent component, int amount)
    {
        super(label);
        this.component = component;
        moveAmount = amount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        component.setLocation((int)component.getLocation().getX() + moveAmount, (int)component.getLocation().getY());        
    }
    
}
