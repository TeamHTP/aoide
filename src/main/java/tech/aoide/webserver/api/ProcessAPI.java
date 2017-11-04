package tech.aoide.webserver.api;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import spark.Route;
import tech.aoide.audio.AudioTrack;

import java.util.ArrayList;

public class ProcessAPI {

    public static Route processString = (req, res) -> {
        // send data to parser
        ProcessRequest processRequest = new Gson().fromJson(req.body(), ProcessRequest.class);
        LoggerFactory.getLogger(ProcessAPI.class).info("\n" + processRequest.getData());
        // return data from audio engine
        res.header("Content-Encoding", "gzip");
        res.type("application/json");
        ArrayList<AudioTrack> tracks = new ArrayList<>();
        return new Gson().toJson(tracks);
    };

}
