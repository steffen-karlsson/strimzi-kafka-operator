/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.api.kafka.model.bridge;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.strimzi.api.kafka.model.common.Constants;
import io.strimzi.api.kafka.model.common.UnknownPropertyPreserving;
import io.strimzi.crdgenerator.annotations.Description;
import io.strimzi.crdgenerator.annotations.DescriptionFile;
import io.strimzi.crdgenerator.annotations.Minimum;
import io.sundr.builder.annotations.Buildable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * A representation of the HTTP configuration.
 */
@DescriptionFile
@Buildable(
        editableEnabled = false,
        builderPackage = Constants.FABRIC8_KUBERNETES_API
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"host", "port", "cors", "producer", "consumer"})
@EqualsAndHashCode
@ToString
public class KafkaBridgeHttpConfig implements UnknownPropertyPreserving {
    public static final int HTTP_DEFAULT_PORT = 8080;
    public static final String HTTP_DEFAULT_HOST = "0.0.0.0";

    private int port = HTTP_DEFAULT_PORT;
    private KafkaBridgeHttpCors cors;
    private KafkaBridgeProducerConfig producer = new KafkaBridgeProducerConfig();
    private KafkaBridgeConsumerConfig consumer = new KafkaBridgeConsumerConfig();
    private Map<String, Object> additionalProperties = new HashMap<>(0);

    public KafkaBridgeHttpConfig() {
    }

    public KafkaBridgeHttpConfig(int port) {
        this.port = port;
    }

    @Description("The port which is the server listening on.")
    @JsonProperty(defaultValue = HTTP_DEFAULT_PORT + "")
    @Minimum(1023)
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Description("Configurations for the HTTP Producer.")
    public KafkaBridgeProducerConfig getProducer() {
        return producer;
    }

    public void setProducer(KafkaBridgeProducerConfig producer) {
        this.producer = producer;
    }

    @Description("Configurations for the HTTP Consumer.")
    public KafkaBridgeConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaBridgeConsumerConfig consumer) {
        this.consumer = consumer;
    }

    @Description("CORS configuration for the HTTP Bridge.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public KafkaBridgeHttpCors getCors() {
        return cors;
    }

    public void setCors(KafkaBridgeHttpCors cors) {
        this.cors = cors;
    }

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @Override
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
