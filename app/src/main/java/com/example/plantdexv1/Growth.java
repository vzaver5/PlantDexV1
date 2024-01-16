package com.example.plantdexv1;

class Growth {
    public String description;
    public String sowing;
    public int days_to_harvest;
    public RowSpacing row_spacing;
    public Spread spread;
    public int ph_maximum;
    public int ph_minimum;
    public int light;
    public int atmospheric_humidity;
    public String[] growth_months;
    public String[] bloom_months;
    public String[] fruit_months;
    public MinimumPrecipitation minimum_precipitation;
    public MaximumPrecipitation maximum_precipitation;
    public MinimumRootDepth minimum_root_depth;
    public MinimumTemperature minimum_temperature;
    public MaximumTemperature maximum_temperature;
    public int soil_nutriments;
    public int soil_salinity;
    public int soil_texture;
    public int soil_humidity;

    public Growth(String description, String sowing, int days_to_harvest, RowSpacing row_spacing, Spread spread, int ph_maximum, int ph_minimum, int light, int atmospheric_humidity, String[] growth_months, String[] bloom_months, String[] fruit_months, MinimumPrecipitation minimum_precipitation, MaximumPrecipitation maximum_precipitation, MinimumRootDepth minimum_root_depth, MinimumTemperature minimum_temperature, MaximumTemperature maximum_temperature, int soil_nutriments, int soil_salinity, int soil_texture, int soil_humidity) {
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
        public int getMm() {
            return mm;
        }

        public int mm;
    }

    class MinimumPrecipitation{
        public int mm;

        public int getMm() {
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
        public int deg_f;
        public int deg_c;

        public int getDeg_f() {
            return deg_f;
        }

        public int getDeg_c() {
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

    public int getPh_maximum() {
        return ph_maximum;
    }

    public int getPh_minimum() {
        return ph_minimum;
    }

    public MinimumTemperature getMinimum_temperature() {
        return minimum_temperature;
    }

    public MaximumTemperature getMaximum_temperature() {
        return maximum_temperature;
    }
}
