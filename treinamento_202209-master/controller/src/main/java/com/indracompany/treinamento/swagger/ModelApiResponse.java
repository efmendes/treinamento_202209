package com.indracompany.treinamento.swagger;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen")
public class ModelApiResponse {

  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("message")
  private String message = null;

  public ModelApiResponse code(final Integer code) {
    this.code = code;
    return this;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    final ModelApiResponse _apiResponse = (ModelApiResponse) o;
    return Objects.equals(this.code, _apiResponse.code) && Objects.equals(this.type, _apiResponse.type) && Objects.equals(this.message, _apiResponse.message);
  }

  @ApiModelProperty(value = "")
  public Integer getCode() {
    return this.code;
  }

  @ApiModelProperty(value = "")
  public String getMessage() {
    return this.message;
  }

  @ApiModelProperty(value = "")
  public String getType() {
    return this.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.code, this.type, this.message);
  }

  public ModelApiResponse message(final String message) {
    this.message = message;
    return this;
  }

  public void setCode(final Integer code) {
    this.code = code;
  }

  public void setMessage(final String message) {
    this.message = message;
  }


  public void setType(final String type) {
    this.type = type;
  }

  private String toIndentedString(final java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("class ModelApiResponse {\n");

    sb.append("    code: ").append(this.toIndentedString(this.code)).append("\n");
    sb.append("    type: ").append(this.toIndentedString(this.type)).append("\n");
    sb.append("    message: ").append(this.toIndentedString(this.message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public ModelApiResponse type(final String type) {
    this.type = type;
    return this;
  }
}

