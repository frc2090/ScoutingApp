package io.github.frc2090.scouting.database;


import io.github.frc2090.scouting.util.Event;
import io.github.frc2090.scouting.util.Team;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Main class for program
 */
public class InfoLoader {

    public static DBHandler handler;

    public static void main(String[] args) {
        try {
            for (Team t: getTeams("hiho")) {
                System.out.println(t.ID);
            }
        } catch (Exception e) {}
    }

    public static ArrayList<Team> getTeams(String evID) {
        try (InputStream is = getPage("https://www.thebluealliance.com/api/v2/event/2017" + evID + "/teams");
             JsonReader rdr = Json.createReader(is)) {
            JsonArray arr = rdr.readArray();
            ArrayList<Team> ret = new ArrayList<Team>();
            for (JsonObject obj : arr.getValuesAs(JsonObject.class)) {
                ret.add(toTeam(obj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Team toTeam(JsonObject obj) {
        return new Team(
                obj.getJsonNumber("team_number").intValueExact(),
                obj.getJsonNumber("rookie_year").intValueExact(),
                obj.getJsonString("nickname").getString(),
                obj.getJsonString("name").getString(),
                obj.getJsonString("location").getString(),
                getWebsite(obj)
        );
    }

    public static String getWebsite(JsonObject obj) {
        String s = "";
        try {
            s = obj.getJsonString("website").getString();
        } catch (Exception e) {
            s = "";
        } finally {
            return s;
        }
    }

    public ArrayList<Event> getEventByTeam(int team) {
        return null;
    }

    public static InputStream getPage(String surl) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(surl);
            request.addHeader("X-TBA-App-Id", "frc2090:scouting-system:v0.001");
            HttpResponse response = httpClient.execute(request);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            return response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
