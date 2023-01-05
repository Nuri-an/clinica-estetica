/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view.util;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author darloonlino
 */
public abstract class CalendarBase extends JPanel {

    protected CalendarBase() {
        calendar = new Calendar();
        calendar.setCurrentTime(DateTime.now());
        calendar.setDate(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay()));
        calendar.setEndDate(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 6));


        content = new JPanel();
        content.setBackground(new Color(242, 242, 242));
        content.setLayout(new GridLayout(1, 1));


        this.add(content);
    }

    public void setInfo(String value) {
        label.setText("<div style=\"padding: 2pt; font-family: Verdana; font-size: 11pt;\">" + value + "</div>");
        label.setCaretPosition(0);
    }

    private static final long serialVersionUID = 1L;

    protected Calendar calendar;
    protected JPanel content;
    private JTextPane label;
}
