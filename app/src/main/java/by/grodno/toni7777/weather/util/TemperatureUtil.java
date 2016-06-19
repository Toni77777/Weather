package by.grodno.toni7777.weather.util;

public final class TemperatureUtil {

    private static final String CELSIUS = "°C";
    private static final String FAHRENHEIT = "°F";

    private TemperatureUtil() {
    }

    public static String tempDegreesCelsius(float temperature) {
        return Math.round(temperature) + CELSIUS;
    }

    public static String tempFahrenheit(float tempCelsius) {
        return convertCelsiusToFahrenheit(tempCelsius) + FAHRENHEIT;
    }

    private static long convertCelsiusToFahrenheit(float tempCelsius) {
        return Math.round(tempCelsius * 1.8 + 32);
    }

}
