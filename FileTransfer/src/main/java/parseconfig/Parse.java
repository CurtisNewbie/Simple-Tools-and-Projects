package parseconfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Parse {

    private static final String IP_REGEX = "IP:\\[([^\\]]*)\\]";
    private static final String PORT_REGEX = "PORT:\\[([^\\]]*)\\]";
    private static final String FILE_PATH_REGEX = "FILE_PATH:\\[([^\\]]*)\\]";;

    private static final Pattern IP_PAT = Pattern.compile(IP_REGEX);
    private static final Pattern PORT_PAT = Pattern.compile(PORT_REGEX);
    private static final Pattern FILE_PATH_PAT = Pattern.compile(FILE_PATH_REGEX);

    /**
     * parse config.txt for sender into an array
     * 
     * @param configPath
     * @return config in String array {ip, port, file_path}
     * @throws IOException if failing to read the config.txt file
     */
    public static String[] parseConfigSender(String configPath) throws IOException {

	var br = new BufferedReader(new FileReader(new File(configPath)));
	String[] config = new String[3];

	int count = 0;
	String line;
	boolean legal = true;
	while (((line = br.readLine()) != null) && legal) {

	    switch (count) {
	    case 0:
		// ip
		var ipMat = IP_PAT.matcher(line);
		if (ipMat.find()) {
		    config[count] = ipMat.group(1);
		} else {
		    legal = false;
		}
		break;
	    case 1:
		// port
		var portMat = PORT_PAT.matcher(line);
		if (portMat.find()) {
		    config[count] = portMat.group(1);
		} else {
		    legal = false;
		}
		break;
	    case 2:
		// file_path
		var fileMat = FILE_PATH_PAT.matcher(line);
		if (fileMat.find()) {
		    config[count] = fileMat.group(1);
		} else {
		    legal = false;
		}
		break;
	    }
	    count++;
	}
	br.close();
	if (legal)
	    return config;
	else
	    return null;

    }

    /**
     * parse config.txt into an array
     * 
     * @param configPath
     * @return config in String array {port, file_path}
     * @throws IOException if failing to read the config.txt file
     */
    public static String[] parseConfigReceiver(String configPath) throws IOException {

	var br = new BufferedReader(new FileReader(new File(configPath)));
	String[] config = new String[2];

	int count = 0;
	String line;
	boolean legal = true;
	while (((line = br.readLine()) != null) && legal) {

	    switch (count) {
	    case 0:
		// port
		var portMat = PORT_PAT.matcher(line);
		if (portMat.find()) {
		    config[count] = portMat.group(1);
		} else {
		    legal = false;
		}
		break;
	    case 1:
		// file_path
		var fileMat = FILE_PATH_PAT.matcher(line);
		if (fileMat.find()) {
		    config[count] = fileMat.group(1);
		} else {
		    legal = false;
		}
		break;
	    }
	    count++;
	}
	br.close();
	if (legal)
	    return config;
	else
	    return null;
    }
}
