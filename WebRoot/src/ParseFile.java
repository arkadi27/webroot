import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseFile {
	
	public JSONObject returnData(String objId) throws FileNotFoundException, ParseException {

		File file = new File("data.txt");
		HashMap<String, Integer> idScore = new HashMap<>();
		String ip;

		
		BufferedReader br = new BufferedReader(new FileReader(file));
		JSONObject myObj = new JSONObject();
		String st;

		try {
			while ((st = br.readLine()) != null) {

				JSONObject jsonObj = new JSONObject(st);
				String id = (String) jsonObj.get("id");
				
				int score = (Integer) jsonObj.get("score");
				
				if(objId.equals(id)){
					ip = (String) jsonObj.get("ip");

					if (idScore.containsKey(id)) {
						idScore.put(id, idScore.get(id) + score);
					} else {
						idScore.put(id, score);
					}
					
					try{
						// since the json object is empty at the beginning
						// calling .get will return a json exception
						// so i need to catch that exception
						myObj.put(ip, myObj.getInt(ip) + 1);
					}
					catch(JSONException exception){
						myObj.put(ip, 1);
						
					}
					myObj.put("id", objId);
					myObj.put("score", idScore.get(objId));
					
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myObj;
		
	}

}
