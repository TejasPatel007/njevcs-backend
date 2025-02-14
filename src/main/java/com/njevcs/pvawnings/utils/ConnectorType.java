/**
 * 
 */
package com.njevcs.pvawnings.utils;

/**
 * @author patel
 *
 *         Nov 15, 2024
 */
public enum ConnectorType {
    NEMA1450(Constants.NEMA1450_VALUE), NEMA515(Constants.NEMA515_VALUE), NEMA520(Constants.NEMA520_VALUE), J1772(Constants.J1772_VALUE), J1772COMBO(
            Constants.J1772COMBO_VALUE), CHADEMO(Constants.CHADEMO_VALUE), TESLA(Constants.TESLA_VALUE);

    ConnectorType(String connectorTypeString) {
        if (!connectorTypeString.equals(this.name()))
            throw new IllegalArgumentException();
    }

    public static class Constants {
        public static final String NEMA1450_VALUE = "NEMA1450";
        public static final String NEMA515_VALUE = "NEMA515";
        public static final String NEMA520_VALUE = "NEMA520";
        public static final String J1772_VALUE = "J1772";
        public static final String J1772COMBO_VALUE = "J1772COMBO";
        public static final String CHADEMO_VALUE = "CHADEMO";
        public static final String TESLA_VALUE = "TESLA";
    }
}
