package com.example.plantdexv1;

class Growth {
    public String description;
    public String sowing;
    public Integer days_to_harvest;
    public RowSpacing row_spacing;
    public Spread spread;
    public Integer ph_maximum;
    public Integer ph_minimum;
    public Integer light;
    public Integer atmospheric_humidity;
    public String[] growth_months;
    public String[] bloom_months;
    public String[] fruit_months;
    public MinimumPrecipitation minimum_precipitation;
    public MaximumPrecipitation maximum_precipitation;
    public MinimumRootDepth minimum_root_depth;
    public MinimumTemperature minimum_temperature;
    public MaximumTemperature maximum_temperature;
    public Integer soil_nutriments;
    public Integer soil_salinity;
    public Integer soil_texture;
    public Integer soil_humidity;

    public Growth(String description, String sowing, Integer days_to_harvest, RowSpacing row_spacing, Spread spread, Integer ph_maximum, Integer ph_minimum, Integer light, Integer atmospheric_humidity, String[] growth_months, String[] bloom_months, String[] fruit_months, MinimumPrecipitation minimum_precipitation, MaximumPrecipitation maximum_precipitation, MinimumRootDepth minimum_root_depth, MinimumTemperature minimum_temperature, MaximumTemperature maximum_temperature, Integer soil_nutriments, Integer soil_salinity, Integer soil_texture, Integer soil_humidity) {
        this.description = description;
        this.sowing = sowing;
        this.days_to_harvest = days_to_harvest;
        this.row_spacing = row_spacing;
        this.spread = spread;
        this.ph_maximum = ph_maximum;
        this.ph_minimum = ph_minimum;
        this.light = light;
        this.atmospheric_humidity = atmospheric_humidity;
        this.growth_months = growth_months;
        this.bloom_months = bloom_months;
        this.fruit_months = fruit_months;
        this.minimum_precipitation = minimum_precipitation;
        this.maximum_precipitation = maximum_precipitation;
        this.minimum_root_depth = minimum_root_depth;
        this.minimum_temperature = minimum_temperature;
        this.maximum_temperature = maximum_temperature;
        this.soil_nutriments = soil_nutriments;
        this.soil_salinity = soil_salinity;
        this.soil_texture = soil_texture;
        this.soil_humidity = soil_humidity;
    }

    class MaximumPrecipitation{
        public Integer getMm() {
            return mm;
        }

        public Integer mm;
    }

    class MinimumPrecipitation{
        public Integer mm;

        public Integer getMm() {
            return mm;
        }
    }

    class MaximumTemperature{
        public Object deg_f;
        public Object deg_c;

        public Object getDeg_f() {
            return deg_f;
        }

        public Object getDeg_c() {
            return deg_c;
        }
    }
    class MinimumTemperature{
        public Integer deg_f;
        public Integer deg_c;

        public Integer getDeg_f() {
            return deg_f;
        }

        public Integer getDeg_c() {
            return deg_c;
        }
    }

    class MinimumRootDepth{
        public Object cm;
    }
    class RowSpacing{
        public Object cm;
    }

    class Spread{
        public Object cm;
    }

    public MinimumPrecipitation getMinimum_precipitation() {
        return minimum_precipitation;
    }

    public MaximumPrecipitation getMaximum_precipitation() {
        return maximum_precipitation;
    }

    public Integer getPh_maximum() {
        return ph_maximum;
    }

    public Integer getPh_minimum() {
        return ph_minimum;
    }

    public MinimumTemperature getMinimum_temperature() {
        return minimum_temperature;
    }

    public MaximumTemperature getMaximum_temperature() {
        return maximum_temperature;
    }
}
