package TMobile.frameworkConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class SetSuite {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     *
     */
    private static Function<String, File> getFrameworkParametersFile = filename -> {
        ClassLoader cl = SetSuite.class.getClassLoader();
        return new File(Objects.requireNonNull(cl.getResource(filename)).getFile());
    };

    /**
     *
     */
    public static void getFrameworkSettings() {
        File frameworkSettingsFile = getFrameworkParametersFile.apply("frameworkSettings.json");
        JSONParser parser = new JSONParser();
        try (Reader fileReader = new FileReader(frameworkSettingsFile)) {
            Object object = parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray parameters = (JSONArray) jsonObject.get("parameter");
            FrameworkParameter.setTestEnvIpAddress(getParameterValue("URI", parameters));
            FrameworkParameter.setBrowser(getParameterValue("Browser", parameters));
            FrameworkParameter.setRemoteTestEnvIpAddress(getParameterValue("remoteURL", parameters));
        } catch (IOException | ParseException e) {
            LOGGER.info(e.getMessage());
        }
    }

    /**
     * @param nameOfParameter
     * @param parameters
     * @return
     */
    private static String getParameterValue(String nameOfParameter, JSONArray parameters) {
        String valueFromParameter = "";
        for (Object param : parameters) {
            Map address = (Map) param;
            Iterator itr1 = address.entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = (Map.Entry) itr1.next();
                if (pair.getValue().equals(nameOfParameter)) {
                    Map.Entry pair2 = (Map.Entry) itr1.next();
                    valueFromParameter = String.valueOf(pair2.getValue());
                }
            }
        }
        return valueFromParameter;
    }
}
