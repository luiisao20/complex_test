package com.luiisao20.utils;

import java.time.OffsetDateTime;

public class Helper {
  public static String formatDate(OffsetDateTime date) {
    String day = Integer.toString(date.getDayOfMonth());
    String month = date.getMonth().name().toLowerCase();
    String year = Integer.toString(date.getYear());
    return day + "/" + month + "/" + year;
  }
}
