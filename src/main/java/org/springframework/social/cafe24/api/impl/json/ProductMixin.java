package org.springframework.social.cafe24.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProductMixin extends Cafe24ObjectMixin {

    private static final Logger logger = LoggerFactory.getLogger(ProductMixin.class);
    static {
        logger.info("ProductMixin  called");
    }
    @JsonCreator
    ProductMixin(@JsonProperty("shop_no") Long shopNo,
                 @JsonProperty("product_no") String productNo,
                 @JsonProperty("product_code") String productCode,
                 @JsonProperty("product_name") String productName,
                 @JsonProperty("detail_image") String detailImage ){}
}
