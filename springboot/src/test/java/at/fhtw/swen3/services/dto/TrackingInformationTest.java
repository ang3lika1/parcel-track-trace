package at.fhtw.swen3.services.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackingInformationTest {

    @Test
    void setState() {
        TrackingInformation trackingInformation = TrackingInformation.builder().state(TrackingInformation.StateEnum.PICKUP).build();
        trackingInformation.setState(TrackingInformation.StateEnum.DELIVERED);
    }
}