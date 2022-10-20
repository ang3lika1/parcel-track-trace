package at.fhtw.swen3.services.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Warehouse
 */


@JsonTypeName("warehouse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T13:39:57.022856Z[Etc/UTC]")
public class WarehouseDto extends HopDto {

  @JsonProperty("level")
  private Integer level;

  @JsonProperty("nextHops")
  @Valid
  private List<WarehouseNextHopDto> nextHops = new ArrayList<>();

  public WarehouseDto level(Integer level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
  */
  @NotNull 
  @Schema(name = "level", required = true)
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public WarehouseDto nextHops(List<WarehouseNextHopDto> nextHops) {
    this.nextHops = nextHops;
    return this;
  }

  public WarehouseDto addNextHopsItem(WarehouseNextHopDto nextHopsItem) {
    this.nextHops.add(nextHopsItem);
    return this;
  }

  /**
   * Next hops after this warehouse (warehouses or trucks).
   * @return nextHops
  */
  @NotNull @Valid 
  @Schema(name = "nextHops", description = "Next hops after this warehouse (warehouses or trucks).", required = true)
  public List<WarehouseNextHopDto> getNextHops() {
    return nextHops;
  }

  public void setNextHops(List<WarehouseNextHopDto> nextHops) {
    this.nextHops = nextHops;
  }

  public WarehouseDto hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public WarehouseDto code(String code) {
    super.setCode(code);
    return this;
  }

  public WarehouseDto description(String description) {
    super.setDescription(description);
    return this;
  }

  public WarehouseDto processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public WarehouseDto locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public WarehouseDto locationCoordinates(GeoCoordinateDto locationCoordinates) {
    super.setLocationCoordinates(locationCoordinates);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WarehouseDto warehouse = (WarehouseDto) o;
    return Objects.equals(this.level, warehouse.level) &&
        Objects.equals(this.nextHops, warehouse.nextHops) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, nextHops, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Warehouse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    nextHops: ").append(toIndentedString(nextHops)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

