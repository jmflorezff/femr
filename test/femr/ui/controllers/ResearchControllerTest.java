package femr.ui.controllers;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import femr.business.services.core.IMedicationService;
import femr.business.services.core.IMissionTripService;
import femr.business.services.core.IResearchService;
import femr.business.services.core.ISessionService;
import femr.utd.tests.BaseTest;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for research controller.
 */
public class ResearchControllerTest extends BaseTest {
    private static ISessionService sessionService;
    private static IResearchService researchService;
    private static IMedicationService medicationService;
    private static IMissionTripService missionTripService;

    private ResearchController researchController;

    @Before
    public void setup() {
        researchController = new ResearchController(sessionService, researchService,
                medicationService, missionTripService);
    }

    @Test
    public void testIndexPost() throws Exception {
        ImmutableMap<String, String> headers = ImmutableMap.<String, String>builder()
                .put("primaryDataset", "age")
                .put("startDate", "1999-01-01 00:00:00")
                .put("endDate", "2016-01-01 00:00:00")
                .put("groupFactor", "1")
                .put("MissionTripId", "2")
                .build();

        RequestBuilder requestBuilder =
                new RequestBuilder().bodyForm(headers);
        Result result =
                new Helpers().invokeWithContext(requestBuilder, () -> researchController.indexPost());

        assertEquals(Http.Status.OK, result.status());

        String contentString = Helpers.contentAsString(result);
        assertTrue(contentString.contains("\"totalPatients\":551.0,"));
        assertTrue(contentString.contains("\"totalEncounters\":592.0,"));
    }

    @Inject
    public void setSessionService(ISessionService sessionService) {
        ResearchControllerTest.sessionService = sessionService;
    }

    @Inject
    public void setResearchService(IResearchService researchService) {
        ResearchControllerTest.researchService = researchService;
    }

    @Inject
    public void setMedicationService(IMedicationService medicationService) {
        ResearchControllerTest.medicationService = medicationService;
    }

    @Inject
    public void setiMissionTripService(IMissionTripService iMissionTripService) {
        ResearchControllerTest.missionTripService = iMissionTripService;
    }

}