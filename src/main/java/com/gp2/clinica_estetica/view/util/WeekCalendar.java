/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view.util;

import com.gp2.clinica_estetica.controller.AttendanceController;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import com.gp2.clinica_estetica.view.FrAttendance;
import com.mindfusion.common.DateTime;
import com.mindfusion.common.Duration;
import com.mindfusion.drawing.Align;
import com.mindfusion.drawing.AwtGraphics;
import com.mindfusion.drawing.Brush;
import com.mindfusion.drawing.Colors;
import com.mindfusion.drawing.DashStyle;
import com.mindfusion.drawing.GradientBrush;
import com.mindfusion.drawing.Pen;
import com.mindfusion.drawing.Pens;
import com.mindfusion.drawing.SolidBrush;
import com.mindfusion.drawing.TextFormat;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarDrawEvent;
import com.mindfusion.scheduling.CalendarElement;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.Cursor;
import com.mindfusion.scheduling.CustomDrawElements;
import com.mindfusion.scheduling.DateEvent;
import com.mindfusion.scheduling.ItemConfirmEvent;
import com.mindfusion.scheduling.ItemDrawContext;
import com.mindfusion.scheduling.ItemModifyConfirmEvent;
import com.mindfusion.scheduling.ItemTooltipEvent;
import com.mindfusion.scheduling.SelectionStyle;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Item;
import com.mindfusion.scheduling.model.ItemEvent;
import com.mindfusion.scheduling.model.ItemList;
import com.mindfusion.scheduling.model.Style;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author darloonlino
 */
public class WeekCalendar extends CalendarBase {

    private Calendar calendar;

    private final Color lineColor = new Color(0xDD, 0xDD, 0xDD);
    private final Color timelineTextColor = new Color(0x66, 0x66, 0x66);
    private final Color timelineFillColor = new Color(0xF6, 0xF9, 0xFC);
    private final Font timelineFont = new Font("Arial", Font.PLAIN, 14);
    private final Color headerTextColor = new Color(0x22, 0x00, 0xCC);
    private final Font headerFont = new Font("Arial", Font.PLAIN, 14);
    private final Color todayDarkColor = new Color(0xFA, 0xD1, 0x63);
    private final Color todayLightColor = new Color(0xFF, 0xF7, 0xD7);
    private final Color nowColor = new Color(0xFF, 0x7F, 0x6E);
    private final Color frameColor = new Color(0xBB, 0xCC, 0xFF);

    private Rectangle currentColumnBounds;
    private ItemList allItems;
    private JFrame frame;
    private User user;

    private int infoColumn = -1;
    private boolean ignoreNextClick = false;

    private static final long serialVersionUID = 1L;

    public WeekCalendar(Dimension size, JFrame frame, User user) {
        this.frame = frame;
        this.user = user;
        setSize(size);
        calendar = new Calendar();
        calendar.beginInit();
        calendar.setCurrentView(CalendarView.Timetable);
        calendar.setEnableDragCreate(true);

        calendar.getTimetableSettings().getTimelineStyle().setHeaderTextColor(Colors.Transparent);
        calendar.getTimetableSettings().getTimelineStyle().setLineColor(Colors.Transparent);
        calendar.getTimetableSettings().getTimelineStyle().setTextColor(Colors.Transparent);

        calendar.getTimetableSettings().getStyle().setHeaderTextColor(headerTextColor);
        calendar.getTimetableSettings().getStyle().setHeaderFont(headerFont);

        calendar.getTimetableSettings().getCellStyle().setBorderLeftColor(lineColor);
        calendar.getTimetableSettings().getCellStyle().setBorderTopColor(Colors.Transparent);
        calendar.getTimetableSettings().getCellStyle().setBorderRightColor(lineColor);
        calendar.getTimetableSettings().getCellStyle().setBorderBottomColor(Colors.Transparent); // this will be custom-drawn depending on cell
        calendar.getTimetableSettings().getCellStyle().setLineColor(lineColor);

        calendar.getTimetableSettings().setHeaderDateFormat("EEE dd/MM");
        calendar.getTimetableSettings().setMainHeaderSize(18);
        //calendar.getTimetableSettings().setInfoHeaderSize(7);
        calendar.getTimetableSettings().setShowWorkTime(false);
        calendar.getTimetableSettings().setShowDayHeader(true);
        //calendar.getTimetableSettings().setShowInfoHeader(true);
        calendar.getTimetableSettings().setColumnBandSize(0);
        calendar.getTimetableSettings().setVisibleColumns(5);

        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 1));
        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 2));
        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 3));
        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 4));
        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 5));
        calendar.getTimetableSettings().getDates().add(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), DateTime.today().getDay() + 6));

        calendar.setCustomDraw(EnumSet.of(
                CustomDrawElements.TimetableTimelineHourCell,
                CustomDrawElements.TimetableWholeDayHeader,
                CustomDrawElements.TimetableCell,
                CustomDrawElements.TimetableColumnHeader,
                CustomDrawElements.TimetableInfoHeader));

        Style personalEvents = new Style();
        personalEvents.setTextColor(new Color(84, 84, 84));
        personalEvents.setTextTopMargin(8);
        personalEvents.setBorderTopColor(Colors.Goldenrod);
        personalEvents.setBorderLeftColor(Colors.Goldenrod);
        personalEvents.setBorderBottomColor(Colors.Goldenrod);
        personalEvents.setBorderRightColor(Colors.Goldenrod);
        personalEvents.setLineColor(Colors.Goldenrod);
        personalEvents.setFillColor(new Color(250, 200, 100));
        personalEvents.setBrush(new GradientBrush(Colors.White, Colors.PaleGoldenrod, 90));
        personalEvents.setHeaderTextColor(Colors.DarkGoldenrod);
        calendar.getItemSettings().setStyle(personalEvents);
        calendar.getItemSettings().setSelectedItemStyle(personalEvents);

        calendar.setShowToolTips(true);

        calendar.getItemSettings().setMoveBandSize(0);
        calendar.getItemSettings().setResizeBandSize(0);
        // TODO:
        //calendar.setAllowInplaceCreate(false);

        calendar.getSelection().setStyle(SelectionStyle.None);
        calendar.getSelection().getSelectedElementsStyle().setBrush(new SolidBrush(new Color(0, 0, 0, 20)));

        calendar.setPreferredSize(new Dimension(size.width, size.height - 5));
        calendar.endInit();

        // this.setBackground(Color.yellow);
        this.add(calendar);

        // overrides
        calendar.addCalendarListener(new CalendarAdapter() {
            @Override
            public void draw(CalendarDrawEvent e) {
                onCalendarDraw(e);
            }

            @Override
            public void hiddenItemClick(DateEvent e) {
                onCalendarHiddenItemClick(e);
            }

            // Disable all interactions while the info box is open
            @Override
            public void itemModifying(ItemModifyConfirmEvent e) {
                System.out.println("type: " + e.getItem().getDescriptionText());
                //e.setConfirm(false);
                onCalendarItemModifying(e);
            }

            @Override
            public void itemInplaceEditStarting(ItemConfirmEvent e) {
                onCalendarItemInplaceEditStarting(e);
            }

            @Override
            public void itemSelecting(ItemConfirmEvent e) {
                onCalendarItemSelecting(e);
            }

            @Override
            public void itemTooltipDisplaying(ItemTooltipEvent e) {
                onCalendarItemTooltipDisplaying(e);
            }

            @Override
            public void itemCreated(ItemEvent e) {
                onItemCreated(e);
            }
        });

        calendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    onCalendarClicked(e);
                } else if (e.getClickCount() == 2) {
                    onCalendarDoubleClicked(e);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                onCalendarMouseMoved(e);
            }
        });

        calendar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onCalendarSizeChanged(e);
            }
        });

        content.add(calendar);
    }

    private void onCalendarMouseMoved(MouseEvent e) {
        if (calendar.getItemAt(e.getX(), e.getY()) != null) {
            calendar.setCalendarCursor(Cursor.Hand);
        } else {
            calendar.setCalendarCursor(Cursor.Default);
        }
    }

    protected void onCalendarItemTooltipDisplaying(ItemTooltipEvent e) {
        if (e.getItem().getAllDayEvent()) {
            e.setTooltip(e.getItem().getHeaderText() + "\n" + e.getItem().getDescriptionText());
        } else {
            e.setTooltip("");
        }
    }

    private void onItemCreated(ItemEvent e) {

        e.getItem().setAllowMove(false);
        e.getItem().setAllowChangeStart(false);
        e.getItem().setAllowChangeEnd(false);
        System.out.println("create");
        //calendar.startInplaceEdit(e.getItem());
        System.out.println("item criado: " + e.getItem().getDescriptionText() + "; " + e.getItem().getHeaderText());
        System.out.println("\n Datas: " + e.getItem().getStartTime().toString() + " à " + e.getItem().getEndTime().toString());
        // go to create appointment screen

        String[] options = {"Sim, como uma avaliação", "Sim, como uma consulta", "Não"};
        int response = JOptionPane.showOptionDialog(null, "Deseja criar esse agendamento?",
                "Selecione a ação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        if (response == 0 || response == 1) {
            String type;
            if (response == 0) {
                type = "Avaliacao";
            } else {
                type = "Consulta";
            }
            FrAttendance attendanceScreen = new FrAttendance(
                    this.frame,
                    "create",
                    type,
                    e.getItem().getStartTime().toJavaCalendar(),
                    e.getItem().getEndTime().toJavaCalendar(),
                    this.user);
            attendanceScreen.setVisible(true);
            this.frame.setVisible(false);
        } else if (response == 2) {
            calendar.getSchedule().getAllItems().remove(e.getItem());
        }

    }

    private void onCalendarItemSelecting(ItemConfirmEvent e) {
        System.out.println("Selecionou: " + e.getItem().getHeaderText());
        if (infoColumn != -1) {
            e.setConfirm(false);
        }
    }

    private void onCalendarItemInplaceEditStarting(ItemConfirmEvent e) {
        if (!e.getItem().getDescriptionText().equals("")) {
            e.setConfirm(false);
        }
    }

    private void onCalendarItemModifying(ItemModifyConfirmEvent e) {
        System.out.println(e.getItem().getDescriptionText() + ", " + e.getItem().getHeaderText());

        //Point p = new Point(e.getItem().getgetX(), e.getY());
        ItemList items = calendar.getSchedule().getAllItems();
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).getId().equals(e.getItem().getId())
                    && items.get(i).getStartTime().getDay() == e.getItem().getStartTime().getDay()) {

                boolean starOfEvent = DateTime.op_GreaterThanOrEqual(items.get(i).getEndTime(), e.getItem().getStartTime())
                        && DateTime.op_LessThanOrEqual(items.get(i).getEndTime(), e.getItem().getEndTime());

                boolean endOfEvent = DateTime.op_GreaterThanOrEqual(items.get(i).getStartTime(), e.getItem().getStartTime())
                        && DateTime.op_LessThanOrEqual(items.get(i).getStartTime(), e.getItem().getEndTime());

                boolean middleOfEvent = DateTime.op_GreaterThanOrEqual(items.get(i).getStartTime(), e.getItem().getStartTime())
                        && DateTime.op_LessThanOrEqual(items.get(i).getStartTime(), e.getItem().getEndTime())
                        && DateTime.op_GreaterThanOrEqual(items.get(i).getEndTime(), e.getItem().getStartTime())
                        && DateTime.op_LessThanOrEqual(items.get(i).getEndTime(), e.getItem().getEndTime());

                boolean outOfEvent = DateTime.op_LessThanOrEqual(items.get(i).getStartTime(), e.getItem().getStartTime())
                        && DateTime.op_GreaterThanOrEqual(items.get(i).getEndTime(), e.getItem().getEndTime());

                System.out.println(starOfEvent + ", " + endOfEvent + ", " + middleOfEvent);
                if (starOfEvent || endOfEvent || middleOfEvent || outOfEvent) {
                    e.setConfirm(false);
                }
            }
        }
        if (!e.getItem().getDescriptionText().equals("")) {
            e.setConfirm(false);
        }
        if (infoColumn != -1) {
            e.setConfirm(false);
        }
    }

    private void onCalendarDoubleClicked(MouseEvent e) {
        System.out.println("double click");
        // If the click is within the currently displayed info box, do
        // not close the info box; instead try to hit-test the items
        // in the info box
        Point point = new Point(e.getXOnScreen() - calendar.getLocationOnScreen().x,
                e.getYOnScreen() - calendar.getLocationOnScreen().y);
        ItemList items = calendar.getSchedule().getAllItems();
        for (Item item : items) {
            Rectangle itemBounds = calendar.getItemBounds(item);
            System.out.println("item bounds: " + itemBounds);

            if (itemBounds.contains(point)) {
                if (item.getHeaderText().equals("")) {
                    return;
                }
                String[] options = {"editar", "excluir", "fechar"};
                int response = JOptionPane.showOptionDialog(null, "Deseja editar ou excluir esse atendimento?",
                        "Selecione a ação",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                if (response == 0) {
                    FrAttendance attendanceScreen = new FrAttendance(this.frame, "edit", item.getTag().toString(), Integer.parseInt(item.getId()), this.user);
                    attendanceScreen.setVisible(true);
                    this.frame.setVisible(false);
                } else if (response == 1) {
                    int responseDel = JOptionPane.showConfirmDialog(null,
                            "Tem certeza quedeseja delatar e liberar o horario de "
                            + item.getStartTime().getDayOfYear()
                            + "/"
                            + item.getStartTime().getMonth()
                            + " - "
                            + item.getStartTime().getHour()
                            + ":"
                            + item.getStartTime().getMinute()
                            + " à "
                            + item.getEndTime().getDayOfYear()
                            + "/"
                            + item.getEndTime().getMonth()
                            + " - "
                            + item.getEndTime().getHour()
                            + ":"
                            + item.getEndTime().getMinute(),
                            "Exclusão solicitada!",
                            JOptionPane.YES_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (responseDel == JOptionPane.YES_OPTION) {
                        AttendanceController attendanceCon = new AttendanceController();
                        try {
                            attendanceCon.onDelete(Integer.parseInt(item.getId()));
                            calendar.getSchedule().getAllItems().remove(item);
                        } catch (AttendanceException ex) {
                            JOptionPane.showMessageDialog(this.frame, ex.getMessage());
                        }
                    }

                }
            }
        }

        return;

    }

    // verify
    private void onCalendarHiddenItemClick(DateEvent e) {
        // Find the index of the column
        System.out.println("hidden item click");
        int index = 0;
        for (DateTime date : calendar.getTimetableSettings().getDates()) {
            if (date.equals(e.getDate())) {
                break;
            }

            index++;
        }

        // If the same column is already displayed, ignore the click
        if (infoColumn == index) {
            return;
        }

        Rectangle headerBounds = calendar.getElementBounds(CalendarElement.TimetableColumnHeader, index);
        // Expand the rectangle to the bottom of the view
        int headerBoundsH = calendar.getHeight() - headerBounds.y - 2;
        int headerBoundsY = headerBounds.y + calendar.getTimetableSettings().getMainHeaderSize();
        headerBoundsH -= calendar.getTimetableSettings().getMainHeaderSize();

        currentColumnBounds = new Rectangle(headerBounds.x, headerBoundsY, headerBounds.width, headerBoundsH);
        infoColumn = index;
        calendar.getSelection().setEnabled(false);
        calendar.getItemSettings().setShowMoreItemsCue(false);

        // Get all items in the column
        allItems = calendar.getSchedule().getAllItems(e.getDate(), e.getDate().addDays(1));

        calendar.invalidate();

        ignoreNextClick = true;
    }

    // verify
    private void onCalendarPaintComponent(Graphics e) {
        System.out.println("paint");
        // Creating a cascading style over Calendar.ItemSettings.Style.
        // All properties that are not explicitly set will propagate to
        // the parent style. Note, that the used constructor is not visible
        // because it was not meant to be used outside of the control.
        Style personalEvents = new Style(calendar.getItemSettings().getStyle());

        // On the other hand you can simply use Calendar.ItemSettings.Style
        //Style personalEvents = calendar1.ItemSettings.Style;
        personalEvents.setTextColor(new Color(0xFF, 0x80, 0x80, 0x80));
        personalEvents.setBorderTopColor(Colors.Goldenrod);
        personalEvents.setBorderLeftColor(Colors.Goldenrod);
        personalEvents.setBorderBottomColor(Colors.Goldenrod);
        personalEvents.setBorderRightColor(Colors.Goldenrod);
        personalEvents.setLineColor(Colors.Goldenrod);
        personalEvents.setFillColor(new Color(250, 200, 100));
        personalEvents.setBrush(new GradientBrush(Colors.White, Colors.PaleGoldenrod, 90));
        personalEvents.setHeaderTextColor(Colors.DarkGoldenrod);

        if (currentColumnBounds != null && !currentColumnBounds.isEmpty()) {
            AwtGraphics g = new AwtGraphics((Graphics2D) e);
            Brush brush = new SolidBrush(new Color(255, 255, 255, 225));
            g.fillRectangle(brush, currentColumnBounds);
            g.drawRectangle(Pens.LightSteelBlue, currentColumnBounds);

            Point p = currentColumnBounds.getLocation();
            allItems
                    .forEach((item) -> {
                        // Instantiating from the ItemDrawContext class - it is done
                        // through reflection because this class is not meant to be
                        // instantiated directly.
                        Constructor<?>[] constructors = ItemDrawContext.class
                                .getConstructors();
                        //	BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Instance);

                        int padding = 2;
                        ItemDrawContext context = null;
                        try {
                            context = (ItemDrawContext) constructors[0].newInstance(
                                    calendar,
                                    item,
                                    g,
                                    new Rectangle(p.x + padding, p.y + padding, currentColumnBounds.width - 2 * padding, calendar.getItemSettings().getSize()),
                                    personalEvents,
                                    true, // Is all-day event ?
                                    true, // Is horizontal ?
                                    true, // Starts here ?
                                    true, // Ends here ?
                                    false, // Is milestone?
                                    false, // Is pointed ?
                                    (Brush) null // Explicit fill brush
                            );
                        } catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e1) {
                        }

                        // Draw the item with its default appearance through the ItemDrawContext
                        if (context != null) {
                            context.drawDefault();
                        }

                        p.y += calendar.getItemSettings().getSize() + padding;
                    });
        }
    }

    private void onCalendarSizeChanged(ComponentEvent e) {
        currentColumnBounds = null;
        infoColumn = -1;
        calendar.getSelection().setEnabled(true);
        calendar.getItemSettings().setShowMoreItemsCue(true);
    }

    private void onCalendarClicked(MouseEvent e) {
        if (ignoreNextClick) {
            ignoreNextClick = false;
        }

        if (currentColumnBounds != null) {
            // If the click is within the currently displayed info box, do
            // not close the info box; instead try to hit-test the items
            // in the info box
            Point point = new Point(e.getXOnScreen() - calendar.getLocationOnScreen().x,
                    e.getYOnScreen() - calendar.getLocationOnScreen().y);
            if (currentColumnBounds.contains(point)) {
                Point p = currentColumnBounds.getLocation();
                for (Item item : allItems) {
                    int padding = 2;

                    Rectangle itemBounds = new Rectangle(p.x + padding, p.y + padding,
                            currentColumnBounds.width - 2 * padding, calendar.getItemSettings().getSize());

                    if (itemBounds.contains(point)) {
                        // Note: Add custom logic here
                        JOptionPane.showMessageDialog(this, item.getHeaderText() + " was clicked!");
                    }

                    p = new Point(p.x, p.y + calendar.getItemSettings().getSize() + padding);
                }

                return;
            }
        }

        currentColumnBounds = null;
        infoColumn = -1;
        calendar.getSelection().setEnabled(true);
        calendar.getItemSettings().setShowMoreItemsCue(true);
        calendar.invalidate();
    }

    // verify
    private void onCalendarDraw(CalendarDrawEvent e) {
        AwtGraphics g = new AwtGraphics(e.getGraphics());
        if (null != e.getElement()) {
            switch (e.getElement()) {
                case TimetableTimelineHourCell: {
                    Rectangle bounds = new Rectangle(e.getBounds());
                    Brush brush = new SolidBrush(timelineFillColor);
                    g.fillRectangle(brush, bounds);
                    bounds.width -= 1;
                    bounds.height -= 1;
                    Pen pen = new Pen(lineColor);
                    g.drawRectangle(pen, bounds);
                    g.drawLine(pen, e.getBounds().getMinX(), e.getBounds().getMinY(),
                            e.getBounds().getMaxX() - 1, e.getBounds().getMinY());
                    DateTime time = e.getDate().add(e.getStartTime());
                    DateTime endTime = e.getDate().add(e.getEndTime());
                    Brush textBrush = new SolidBrush(timelineTextColor);
                    TextFormat format = new TextFormat(Align.Far, Align.Near);
                    g.drawString(String.format("%1$s%2$s", time.toString("h ").trim(), time.getHour() >= 12 ? "pm" : "am"),
                            timelineFont, textBrush, bounds, format);
                    Duration firstVisibleTime = calendar.getFirstVisibleDate().getTimeOfDay();
                    if (Duration.op_LessThanOrEqual(time.getTimeOfDay(), firstVisibleTime)
                            && Duration.op_LessThan(firstVisibleTime, endTime.getTimeOfDay())) {
                        // Bottom line
                        Pen bottomPen = new Pen(frameColor);
                        g.drawLine(bottomPen, e.getBounds().getMinX(), e.getBounds().getMinY() - 1,
                                e.getBounds().getMaxX() - 1, e.getBounds().getMinY() - 1);
                    }
                    break;
                }
                case TimetableWholeDayHeader: {
                    boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());
                    boolean isTomorrow = DateTime.op_Equality(e.getDate().addDays(-1), DateTime.today());
                    Pen pen = new Pen(lineColor);
                    g.drawLine(pen, e.getBounds().getMaxX() - 1, e.getBounds().getMinY(),
                            e.getBounds().getMaxX() - 1, e.getBounds().getMaxY());
                    if (isToday || isTomorrow) {
                        // Fill
                        if (isToday) {
                            Brush brush = new SolidBrush(todayLightColor);
                            g.fillRectangle(brush, e.getBounds());
                        }

                        // Left/right side
                        Pen todayPen = new Pen(todayDarkColor);
                        g.drawLine(todayPen, e.getBounds().getMinX() - 1, e.getBounds().getMinY(),
                                e.getBounds().getMinX() - 1, e.getBounds().getMaxY() - 1);
                    }
                    break;
                }
                case TimetableInfoHeader:
                    // Bottom line
                    Pen bottomPen = new Pen(frameColor);
                    g.drawLine(bottomPen, e.getBounds().getMinX(), e.getBounds().getMaxY() - 1,
                            e.getBounds().getMaxX() - 1, e.getBounds().getMaxY() - 1);
                    break;
                case TimetableCell: {
                    DateTime time = e.getDate().add(e.getStartTime());
                    DateTime endTime = e.getDate().add(e.getEndTime());
                    boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());
                    boolean isTomorrow = DateTime.op_Equality(e.getDate().addDays(-1), DateTime.today());
                    Pen pen = new Pen(lineColor);
                    Pen todayPen = new Pen(todayDarkColor);
                    if (time.getMinute() == 0) {
                        pen.setDashStyle(DashStyle.Dot);
                    } else {
                        pen.setDashStyle(DashStyle.Solid);
                    }       // Fill
                    if (isToday) {
                        Brush brush = new SolidBrush(todayLightColor);
                        g.fillRectangle(brush, e.getBounds().getX() + 1, e.getBounds().getY() + 1,
                                e.getBounds().getWidth() - 1, e.getBounds().getHeight() - 1);
                    }       // Bottom side
                    g.drawLine(pen, e.getBounds().getMinX(), e.getBounds().getMaxY() - 1,
                            e.getBounds().getMaxX() - 1, e.getBounds().getMaxY() - 1);
                    if (time.getHour() == 0 && time.getMinute() == 0) {
                        // Top side
                        pen.setDashStyle(DashStyle.Solid);
                        g.drawLine(pen, e.getBounds().getMinX(), e.getBounds().getMinY(),
                                e.getBounds().getMaxX() - 1, e.getBounds().getMinY());
                    }
                    if (isToday || isTomorrow) {
                        // Left side
                        g.drawLine(todayPen, e.getBounds().getMinX(), e.getBounds().getMinY(),
                                e.getBounds().getMinX(), e.getBounds().getMaxY() - 1);
                    }       // Draw the current time indicator
                    DateTime now = DateTime.now();
                    if (DateTime.op_LessThanOrEqual(time, now) && DateTime.op_LessThan(now, endTime)) {
                        double relativePosition = ((double) now.getTicks() - time.getTicks()) / (endTime.getTicks() - time.getTicks());

                        double position = e.getBounds().getMinY() + (int) (e.getBounds().getHeight() * relativePosition);

                        Pen nowPen = new Pen(nowColor, 2);

                        g.drawLine(nowPen, e.getBounds().getMinX() + 3, position, e.getBounds().getMaxX() - 3, position);

                        Point2D[] leftArrow = new Point2D[]{
                            new Point2D.Double(e.getBounds().getMinX() + 1, position - 4),
                            new Point2D.Double(e.getBounds().getMinX() + 1, position + 4),
                            new Point2D.Double(e.getBounds().getMinX() + 4 + 1, position),};
                        Point2D[] rightArrow = new Point2D[]{
                            new Point2D.Double(e.getBounds().getMaxX() - 1, position - 4),
                            new Point2D.Double(e.getBounds().getMaxX() - 1, position + 4),
                            new Point2D.Double(e.getBounds().getMaxX() - 4 - 1, position),};

                        Brush nowBrush = new SolidBrush(nowColor);
                        g.fillPolygon(nowBrush, leftArrow);
                        g.fillPolygon(nowBrush, rightArrow);
                    }
                    break;
                }
                case TimetableColumnHeader: {
                    boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());
                    if (isToday) {
                        // Fill
                        Brush brush = new SolidBrush(todayDarkColor);
                        g.fillRectangle(brush, e.getBounds());

                        // Left side
                        Pen pen = new Pen(todayDarkColor);
                        g.drawLine(pen, e.getBounds().getMinX() - 1, e.getBounds().getMinY(),
                                e.getBounds().getMinX() - 1, e.getBounds().getMaxY() - 1);

                        // Text
                        Brush textBrush = new SolidBrush(e.getStyle().getHeaderTextColor());
                        TextFormat format = new TextFormat(Align.Center, Align.Center);
                        g.drawString(e.getText(), e.getStyle().getHeaderFont(),
                                textBrush, e.getBounds(), format);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void addItem(String title, String patientName, DateTime startDate, DateTime endDate, String id, String type) {
        Appointment appointment = new Appointment();
        appointment.setHeaderText(patientName);
        appointment.setDescriptionText(title);
        appointment.setDetails(title);
        appointment.setStartTime(startDate);
        appointment.setEndTime(endDate);
        appointment.setId(id);
        appointment.setTag(type);

        // configs
        appointment.setAllowMove(false);
        appointment.setAllowChangeStart(false);
        appointment.setAllowChangeEnd(false);
        appointment.setLocked(true);
        calendar.getSchedule().getItems().add(appointment);

    }
    
    public void resizeCalendar(Dimension size){
        setSize(size);
    }

    class GoogleCalendar extends Calendar {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onCalendarPaintComponent(g);
        }

        private static final long serialVersionUID = 1L;
    }
}
