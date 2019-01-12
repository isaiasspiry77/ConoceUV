package conoceuv.com.conoceuv.Modelos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class EdificioModel implements Serializable {
    public String title;
    public boolean classrooms;
    public boolean laboratory;
    public String code;
    public String longitud;
    public String latitud;
    public String descripcion;


    public EdificioModel(JSONObject io) {

        try {
            this.title = io.getString("title");
            this.classrooms = io.getBoolean("classrooms");
            this.laboratory = io.getBoolean("laboratory");
            code = io.getString("code");
            this.longitud = io.getString("longitud");
            this.latitud = io.getString("latitud");
            this.descripcion = io.getString("descripcion");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
