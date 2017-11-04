package tech.aoide.webserver.api;

import com.google.gson.Gson;
import spark.Route;
import tech.aoide.audio.AudioTrack;

import java.util.ArrayList;

public class ProcessAPI {

    public static Route processString = (req, res) -> {
        // send data to parser
        req.params("data");
        // return data from audio engine
        res.type("application/json");
        ArrayList<AudioTrack> tracks = new ArrayList<>();
        return new Gson().toJson(tracks);
    };

}
