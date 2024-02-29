package com.gateway.app.model;

public enum EnumSigns {
    AQUARIUS (1,"aquarius","Jan 20","Feb 18"),
    PISCES (2,"pisces","Feb 19","Mar 20"),
    ARIES (3,"aries","Mar 21","Apr 19"),
    TAURUS (4,"taurus","Apr 20","May 20"),
    GEMINI (4,"gemini","May 21","Jun 20"),
    CANCER (4,"cancer","Jun 21","Jul 22"),
    LEO (4,"leo","Jul 23","Aug 22"),
    VIRGO (4,"virgo","Aug 23","Sep 22"),
    LIBRA (4,"libra","Sep 23","Oct 22"),
    SCORPIO (4,"scorpio","Oct 23","Nov 21"),
    SAGITTARIUS (4,"sagittarius","Nov 22","Dec 21"),
    CAPRICORN (4,"capricorn","Dec 22","Jan 19");
    private int id;
    private String name;
    private String startDay;
    private String endDay;
    EnumSigns(int id, String name, String startDay, String endDay) {
        this.id = id;
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEndDay() {
        return endDay;
    }
}
