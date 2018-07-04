package org.springframework.social.cafe24.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class Product extends Cafe24Object implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    private final Long shopNo;

    private final String productNo;

    private final  String productCode;

    private final String productName;

    private final String detailImage;
    static {
        logger.info("Product.class static block");
    }


    public Product(Long shopNo, String productNo, String productCode, String productName, String detailImage) {
        logger.info("api.Product class constructor called");

        this.shopNo = shopNo;
        this.productNo = productNo;
        this.productCode = productCode;
        this.productName = productName;
        this.detailImage = detailImage;
        logger.info("api.Product instance constructed");

    }

    public Long getShopNo() {
        return shopNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getListIamge() {
        return detailImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "shopNo=" + shopNo +
                ", productNo='" + productNo + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", detailImage=" + detailImage +
                '}';
    }
}
