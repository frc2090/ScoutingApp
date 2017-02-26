package io.github.frc2090.scouting.util;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dchotzen-hartzell19 on 2/22/17.
 */
public class Event {
    public static ArrayList<Event> events = new ArrayList<Event>();
    public final String key;
    public final Date startDate;
    public final Date endDate;
    public final String name;
    public final String location;
    public final ArrayList<Team> teams = new ArrayList<Team>();

    public Event(String key, Date startDate, Date endDate, String name, String location) {
        events.add(this);
        this.key = key;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.location = location;
    }
}
