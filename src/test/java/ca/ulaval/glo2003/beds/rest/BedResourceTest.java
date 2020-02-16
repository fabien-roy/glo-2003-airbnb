package ca.ulaval.glo2003.beds.rest;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.services.BedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedResourceTest {

  private BedResource bedResource;
  private BedService bedService;

  @BeforeEach
  private void setUpResource() {
    bedService = mock(BedService.class);
    bedResource = new BedResource(bedService);
  }

  @Test
  private void add_shouldBedNumberAsHeaderLocation() {
    // TODO
  }

  @Test
  private void add_shouldSetCreatedAsHttpStatus() {
    // TODO
  }

  @Test
  private void get_shouldReturnBed() {
    // TODO
  }

  @Test
  private void get_shouldSetOKAsHttpStatus() {
    // TODO
  }

  @Test
  private void getAll_shouldReturnAllBeds() {
    // TODO
  }

  @Test
  private void getAll_shouldSetOKAsHttpStatus() {
    // TODO
  }
}
