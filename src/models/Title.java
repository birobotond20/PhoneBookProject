package models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Title {

  MR("Mr"),
  MRS("Mrs"),
  MS("Ms"),
  DEFAULT("");

  private final String title;

  private static final Map<String, Title> titleMap = new HashMap<>();

  Title(String title) {
    this.title = title;
  }

  static {
    for (Title title : EnumSet.allOf(Title.class)) {
      titleMap.put(title.toString(), title);
    }
  }


  public static Title getValueFrom(String title) {
    for (Map.Entry<String, Title> currentTitle : titleMap.entrySet()) {
      if (currentTitle.getKey().equalsIgnoreCase(title)) {
        return currentTitle.getValue();
      }
    }

    return DEFAULT;
  }

  @Override
  public String toString() {
    return this.equals(DEFAULT) ? "" :
        title.substring(0, 1).toUpperCase() + title.substring(1);
  }
}
