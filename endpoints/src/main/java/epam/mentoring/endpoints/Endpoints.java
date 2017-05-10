package epam.mentoring.endpoints;

/**
 * Created by Igor_Ponomarenko on 09.05.2017.
 */
public enum Endpoints {
    EVENTS("http://localhost:8082/greetservice/"),
    TICKETS_STORE("http://localhost:8083/store/");

    String systemURL;

    Endpoints(String systemURL) {
        this.systemURL = systemURL;
    }

    public String getSystemURL() {
        return systemURL;
    }
}
