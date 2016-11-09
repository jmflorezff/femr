package femr.business.services.system;

import com.google.inject.Inject;
import femr.business.services.core.ISearchService;
import femr.common.dtos.ServiceResponse;
import femr.common.models.PatientItem;
import femr.utd.tests.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for search service.
 */
public class SearchServiceTest extends BaseTest {
    private static ISearchService searchService;

    @Test
    public void testRetrievePatientsFromQueryString() throws Exception {
        List<PatientItem> patients =
                searchService.retrievePatientsFromQueryString("anon anon").getResponseObject();
        assertTrue(patients.size() >= 1306);
    }

    @Test
    public void testRetrievePatientsForSearch() throws Exception {
        List<PatientItem> patients = searchService.retrievePatientsForSearch(1).getResponseObject();
        assertTrue(patients.size() >= 1306);
    }

    @Inject
    public void setSearchService(ISearchService searchService) {
        SearchServiceTest.searchService = searchService;
    }
}