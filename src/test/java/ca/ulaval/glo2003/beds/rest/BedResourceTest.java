package ca.ulaval.glo2003.beds.rest;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.services.BedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedResourceTest {

  private BedResource bedResource;
  private BedService bedService;

  @BeforeEach
  public void setUpResource() {
    bedService = mock(BedService.class);
    bedResource = new BedResource(bedService);
  }

  @Test
  public void add_shouldBedNumberAsHeaderLocation() {
    // TODO
  }

  @Test
  public void add_shouldSetCreatedAsHttpStatus() {
    // TODO
  }

  @Test
  public void get_shouldReturnBed() {
    // TODO
  }

  @Test
  public void get_shouldSetOKAsHttpStatus() {
    // TODO
  }

  @Test
  public void getAll_shouldReturnAllBeds() {
    // TODO
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    // TODO
  }
}
