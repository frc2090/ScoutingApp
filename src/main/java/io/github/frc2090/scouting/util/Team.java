package io.github.frc2090.scouting.util;

import javax.json.JsonObject;
import java.util.ArrayList;

/**
 * Created by dchotzen-hartzell19 on 2/22/17.
 */
public class Team {

    public static ArrayList<Team> teams = new ArrayList<Team>();
    public final int ID;
    public final int rookieYear;
    public final String nickname;
    public final String fullname;
    public final String location;
    public final String website;

    public Team(int ID, int rookieYear, String nickname, String fullname, String location, String website) {
        teams.add(this);
        this.ID = ID;
        this.rookieYear = rookieYear;
        this.nickname = nickname;
        this.fullname = fullname;
        this.location = location;
        this.website = website;

        System.out.println(String.format("%s %s %s %s %s %s",
                ID, rookieYear, nickname, fullname, location, website));
    }

    public ArrayList<Event> getEvents() {
        ArrayList<Event> ret = new ArrayList<>();
        for (Event e: Event.events) {
            if (e.teams.contains(this)) ret.add(e);
        }
        return ret;
    }

}
