package tech.aoide.webserver.api;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import spark.Route;
import tech.aoide.audio.AudioTrack;
import tech.aoide.interpreter.Interpreter;
import tech.aoide.vm.jsinterpreter.JSVM;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessAPI {

    public static Route processJava = (req, res) -> {
        // send data to parser
        ProcessRequest processRequest = new Gson().fromJson(req.body(), ProcessRequest.class);
        LoggerFactory.getLogger(ProcessAPI.class).info("\n" + processRequest.getData());
        // return data from audio engine
        res.header("Content-Encoding", "gzip");
        res.type("application/json");
        if (processRequest.getLang().equalsIgnoreCase("JavaScript")) {
            JSVM jsvm = new JSVM();
            try {
                ArrayList<AudioTrack> tracks = jsvm.eval(processRequest.getData());
                return new Gson().toJson(tracks);
            }
            catch (ScriptException e) {
                HashMap<String, String> errorMap = new HashMap<>();
                errorMap.put("error", e.getMessage());
                return new Gson().toJson(errorMap);
            }
        }
        else {
            Interpreter interpreter = new Interpreter(processRequest.getData());
            ArrayList<AudioTrack> tracks = interpreter.interpret();
            return new Gson().toJson(tracks);
        }
    };

}
