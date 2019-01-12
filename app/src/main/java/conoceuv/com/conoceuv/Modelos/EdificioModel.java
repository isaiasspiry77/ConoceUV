package conoceuv.com.conoceuv.Modelos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class EdificioModel implements Serializable {
    public String title;
    public boolean classrooms;
    public boolean laboratory;
    public String code;


    public EdificioModel(JSONObject io) {

        try {
            this.title = io.getString("title");
            this.classrooms = io.getBoolean("classrooms");
            this.laboratory = io.getBoolean("laboratory");
            code = io.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClassrooms() {
        return classrooms;
    }

    public void setClassrooms(boolean classrooms) {
        this.classrooms = classrooms;
    }

    public boolean isLaboratory() {
        return laboratory;
    }

    public void setLaboratory(boolean laboratory) {
        this.laboratory = laboratory;
    }
}
