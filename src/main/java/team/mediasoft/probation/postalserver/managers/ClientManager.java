package team.mediasoft.probation.postalserver.managers;

import lombok.Getter;
import lombok.Setter;

/**
 * Class-object client manager.
 */
public class ClientManager {

    /**
     * Variable ip client.
     */
    @Setter
    @Getter
    private String ip;

    /**
     * Variable id authorized client.
     */
    @Setter
    @Getter
    private String id;

    /**
     * Constructor class.
     * @param receivedIp - ip client
     * @param receivedId - id authorized client
     */
    public ClientManager(
            final String receivedIp,
            final String receivedId) {
        this.setId(receivedId);
        this.setIp(receivedIp);
    }

    /**
     * Method get history postal departure.
     * @param idPostalDeparture - id postal departure.
     */
    public final void getHistoryDeparture(
        final int idPostalDeparture) {

    }

    /**
     * Method get status postal departure.
     * @param idPostalDeparture - id postal departure.
     */
    public final void getStatusDeparture(
            final int idPostalDeparture) {

    }

}
